package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Testing (once we are told/taught what framework to use)

/** A set of time slots during which a doctor is available. */
public class AvailabilitySchedule implements Serializable {

    private static final String doctorTimeSlotsDBKey = "available_time_slots";

    private final DatabaseReference ref;
    private final Calendar calendar;
    private Set<DateTimeInterval> timeSlots;

//    public AvailabilitySchedule() {
//        this.timeSlots = new HashSet<>();
//        ref = null;
//        calendar = new GregorianCalendar();
//    }

    /**
     * Construct an AvailabilitySchedule that represents availability of {@code doctor}.
     * @param doctor doctor whose availability is modelled
     * @param calendar calendar to use when calculating days and times
     */
    public AvailabilitySchedule(DatabaseReference doctor, Calendar calendar) {
        this.ref = doctor.child(doctorTimeSlotsDBKey);
        this.calendar = calendar;
        this.timeSlots = new HashSet<>();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                timeSlots = new HashSet<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    timeSlots.add(child.getValue(DateTimeInterval.class));
                }
                Log.d("AvailabilitySchedule#onDataChange", "" + timeSlots);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("AvailabilitySchedule", error.toException());
            }
        });
    }

    /** All currently available time slots. */
    public Set<DateTimeInterval> timeSlots() {
        return timeSlots;
    }
    /**
     * All currently available time slots that start on the same day as {@code dayDate}.
     * @param dayDate date whose day in the schedule's calendar should match that of returned
     *                time slots
     */
    public Set<DateTimeInterval> timeSlotsOnDay(Date dayDate) {
        Calendar day = (Calendar) calendar.clone();
        day.clear();
        day.setTime(dayDate);
        setTimeToMidnight(day);

        Calendar nextDay = (Calendar) day.clone();
        // Advance by one day.
        nextDay.roll(Calendar.DATE, true);

        Set<DateTimeInterval> matches = new HashSet<>();
        for (DateTimeInterval timeSlot : timeSlots) {
            if (timeSlot.getStart().after(day.getTime()) && timeSlot.getStart().before(nextDay.getTime())) {
                matches.add(timeSlot);
            }
        }

        return matches;
    }

    private static void setTimeToMidnight(Calendar day) {
        day.set(Calendar.HOUR, 0);
        day.set(Calendar.MINUTE, 0);
        day.set(Calendar.SECOND, 0);
        day.set(Calendar.MILLISECOND, 0);
    }

    /** Add {@code slot} as an available time slot. */
    public void addTimeSlot(DateTimeInterval slot) {
        Set<DateTimeInterval> newSlots = new HashSet<>(timeSlots);
        newSlots.add(slot);
        ref.setValue(new ArrayList<DateTimeInterval>(newSlots));
//        timeSlots.add(slot);
    }
    /** Remove {@code slot} from the available time slots. */
    public void removeTimeSlot(DateTimeInterval slot) {
        Set<DateTimeInterval> newSlots = new HashSet<>(timeSlots);
        newSlots.remove(slot);
        ref.setValue(new ArrayList<DateTimeInterval>(newSlots));
//        timeSlots.remove(slot);
    }


}
