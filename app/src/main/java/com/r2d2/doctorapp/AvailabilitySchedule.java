package com.r2d2.doctorapp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// TODO: Database integration
// TODO: Testing (once we are told/taught what framework to use)

/** A set of time slots during which a doctor is available. */
public class AvailabilitySchedule {

    private Set<DateTimeInterval> timeSlots;
    private Calendar calendar;

    public AvailabilitySchedule(Calendar calendar) {
        this.calendar = calendar;
    }

    /** All currently available time slots. */
    public Set<DateTimeInterval> timeSlots() {
        return timeSlots;
    }
    /** All currently available time slots that start on the same day as {@code dayDate}. */
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
            if (timeSlot.start().after(day.getTime()) && timeSlot.start().before(nextDay.getTime())) {
                matches.add(timeSlot);
            }
        }

        return matches;
    }

    private void setTimeToMidnight(Calendar day) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /** Synchronize with the database. */
    public void synchronize() {
        // TODO: stub
    }

    /** Add {@code slot} as an available time slot. */
    public void addTimeSlot(DateTimeInterval slot) {
        timeSlots.add(slot);
        synchronize();
    }
    /** Remove {@code slot} from the available time slots. */
    public void removeTimeSlot(DateTimeInterval slot) {
        timeSlots.remove(slot);
        synchronize();
    }

}
