package com.r2d2.doctorapp;

import android.util.Log;

import androidx.annotation.NonNull;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Testing (once we are told/taught what framework to use)

/** A set of time slots during which a doctor is available. */
public class AvailabilitySchedule {

    private Set<DateTimeInterval> timeSlots;

    public AvailabilitySchedule() {
        this.timeSlots = new HashSet<>();
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
//    public Set<DateTimeInterval> timeSlotsOnDay(Date dayDate) {
//        Calendar day = (Calendar) calendar.clone();
//        day.clear();
//        day.setTime(dayDate);
//        setTimeToMidnight(day);
//
//        Calendar nextDay = (Calendar) day.clone();
//        // Advance by one day.
//        nextDay.roll(Calendar.DATE, true);
//
//        Set<DateTimeInterval> matches = new HashSet<>();
//        for (DateTimeInterval timeSlot : timeSlots) {
//            if (timeSlot.getStart().after(day.getTime()) && timeSlot.getStart().before(nextDay.getTime())) {
//                matches.add(timeSlot);
//            }
//        }
//
//        return matches;
//    }

//    private static void setTimeToMidnight(Calendar day) {
//        day.set(Calendar.HOUR, 0);
//        day.set(Calendar.MINUTE, 0);
//        day.set(Calendar.SECOND, 0);
//        day.set(Calendar.MILLISECOND, 0);
//    }

    /** Add {@code slot} as an available time slot. */
    public void addTimeSlot(DateTimeInterval slot) {
        timeSlots.add(slot);
        Log.i("slot", "added " + slot.toString());
    }
    /** Remove {@code slot} from the available time slots. */
    public void removeTimeSlot(DateTimeInterval slot) {
        timeSlots.remove(slot);
        Log.i("slot", "removed " + slot.toString());
    }

}