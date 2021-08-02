package com.r2d2.doctorapp;

// TODO: Move or remove once we begin to properly test the app.

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.GregorianCalendar;

public class TestAvailabilitySchedule {

    public static void run() {
        AvailabilitySchedule schedule = new AvailabilitySchedule(FirebaseDatabase.getInstance().getReference("doctors/test123"), GregorianCalendar.getInstance());
        Log.d("main", "" + schedule.timeSlots());
        Log.d("main", "" + schedule.timeSlotsOnDay(new Date()));

        doIn(3000, () -> {
            Date now = new Date();
            Date later = (Date) now.clone();
            later.setTime(later.getTime() + 100000);
            schedule.addTimeSlot(new DateTimeInterval(now, later));

            doIn(3000, () -> {
                Log.d("main", "" + schedule.timeSlots());
                Log.d("main", "" + schedule.timeSlotsOnDay(new Date()));
            });
        });
    }

    private static void doIn(long ms, Runnable action) {
        new Thread(() -> {
            try {
                Thread.sleep(ms);
            } catch (Exception e) {}
            action.run();
        }).start();
    }

}
