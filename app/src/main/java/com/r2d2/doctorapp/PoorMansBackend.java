package com.r2d2.doctorapp;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public final class PoorMansBackend {

    private static final String LOG_TAG = "PoorMansBackend";

    private static final PoorMansBackend instance = new PoorMansBackend();

    public static PoorMansBackend getInstance() {
        return instance;
    }

    private Lock lock;
    private final UUID ourID = UUID.randomUUID();
    private final Timer timer = new Timer();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void start() {
        if (lock == null) {
            Log.i(LOG_TAG, "Start");
            lock = new Lock(
                FirebaseDatabase.getInstance().getReference("maintenance/lock"),
                () -> {
                    Log.i(LOG_TAG, "Acquired maintenance lock");
                }, () -> {
                    Log.i(LOG_TAG, "Released maintenance lock");
                }, () -> {
                    Log.i(LOG_TAG, "Running maintenance pass");

                    // ------ For register list ------
                    long currentTime = Instant.now().getEpochSecond();
                    DatabaseReference docRef = FirebaseDatabase.getInstance().getReference("Doctors");
                    docRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            for (DataSnapshot docChild : snapshot.getChildren()){
                                for (DataSnapshot appChild : docChild.child("appointments").getChildren()){
                                    Appointment app = appChild.getValue(Appointment.class);
                                    // If there is a patient and the current time has surpassed the appointment time, add to past patients
                                    if (!(app.getPatientName().equals("")) && currentTime > app.getTimeStamp()){
                                        Log.i(LOG_TAG, "adding " + app.getPatientName() + " to " + app.getDoctorName() + " past patients");
                                        // i think this keeps trying to add it to past patients even when its already in the list, so maybe change this?
                                        docRef.child(app.getDoctorName()).child("past patients").child(app.getPatientName()).setValue(true);
                                        appChild.getRef().removeValue();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                    
                }
            );
            lock.start();
        } else {
            Log.i(LOG_TAG, "Already started");
        }
    }

    public void stop() {
        Log.i(LOG_TAG, "Stop");
        lock.stop();
    }

    private class Lock {

        private static final double STEAL_TIMEOUT_DURATION_SECONDS = 30;
        private static final String DB_KEY_CHECK_IN = "check_in";
        private static final String DB_KEY_OWNER = "owner";

        private final DatabaseReference lockRef;
        private final Runnable onAcquired, onReleased, whileOwned;
        private TimerTask timerTask;
        private boolean ownedByUs = false;

        Lock(DatabaseReference lockRef, Runnable onAcquired, Runnable onReleased, Runnable whileOwned) {
            this.lockRef = lockRef;
            this.onAcquired = onAcquired;
            this.onReleased = onReleased;
            this.whileOwned = whileOwned;

            timerTask = new TimerTask() {

                private boolean waitingOnValueEvent = false;

                @Override
                public void run() {
                    Log.d(LOG_TAG, "Lock Timer fired");
                    if (waitingOnValueEvent) {
                        Log.w(LOG_TAG, "Lock still waiting on value event");
                        return;
                    }

                    waitingOnValueEvent = true;
                    lockRef.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            waitingOnValueEvent = false;
                            Log.d(LOG_TAG, "Lock ValueEventListener onDataChange");

                            String owner = snapshot.child(DB_KEY_OWNER).getValue(String.class);
                            Long checkIn = snapshot.child(DB_KEY_CHECK_IN).getValue(Long.class);

                            if (owner == null || checkIn == null) {
                                // It's free real estate
                                Log.i(LOG_TAG, "Acquiring lock (free real estate)");
                                acquireImmediately();
                            } else if (owner.equals(ourID.toString())) {
                                Log.d(LOG_TAG, "Checking in");
                                checkIn();
                            } else {
                                // Some other user has the lock.
                                // Check how long it's been since they've checked in.
                                long then = checkIn;
                                long now = new Date().getTime();
                                double elapsed = (now - then) / 1000.0;
                                if (elapsed > STEAL_TIMEOUT_DURATION_SECONDS) {
                                    // Steal lock.
                                    Log.i(LOG_TAG, "Stealing lock; last check-in was " + elapsed + " seconds ago");
                                    acquireImmediately();
                                } else if (ownedByUs) {
                                    // Another client did the acquire() call above to steal from us.
                                    Log.i(LOG_TAG, "Someone else stole the lock!");
                                    release();
                                } else {
                                    Log.i(LOG_TAG, "Someone else has lock; last check-in was " + elapsed + " seconds ago");
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            waitingOnValueEvent = false;

                            Log.w(LOG_TAG, "Lock ValueEventListener onCancelled: " + error.toException());
                            lockRef.addValueEventListener(this);
                        }

                    });
                }

            };
        }

        public void start() {
            timer.schedule(timerTask, 0, 10 * 1000 /* 10 seconds */);
        }

        public void stop() {
            timerTask.cancel();
            release();
        }

        private void acquireImmediately() {
            lockRef.child(DB_KEY_OWNER).setValue(ourID.toString());
            lockRef.child(DB_KEY_CHECK_IN).setValue(new Date().getTime());
            ownedByUs = true;
            onAcquired.run();
            whileOwned.run();
        }

        private void checkIn() {
            lockRef.child(DB_KEY_CHECK_IN).setValue(new Date().getTime());
            whileOwned.run();
        }

        void release() {
            lockRef.child(DB_KEY_OWNER).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ownedByUs = false;
                    String owner = snapshot.getValue(String.class);
                    if (owner == null || owner.equals(ourID.toString())) {
                        lockRef.removeValue();
                    }
                    onReleased.run();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("PoorMansBackend", "Lock release onCancelled: " + error.toException());
                }
            });
        }

    }

}
