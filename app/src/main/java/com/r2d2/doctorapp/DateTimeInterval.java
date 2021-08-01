package com.r2d2.doctorapp;

import static java.sql.Types.NULL;

/**
 * this version of DateTimeInterval is ONLY for TESTING AvailabilityRecyclerView
 */
public class DateTimeInterval implements Comparable<DateTimeInterval>{
    private String timeSlot;

    public DateTimeInterval(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTimeSlot(){
        return this.timeSlot;
    }

    public void setTimeSlot(DateTimeInterval timeSlot) {
        this.timeSlot = timeSlot.timeSlot;
    }


    public String toString() {
        return timeSlot;
    }


    @Override
    public int compareTo(DateTimeInterval o) {
        return this.timeSlot.compareTo(o.timeSlot);
    }
}
