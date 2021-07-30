package com.r2d2.doctorapp;

/**
 * this version of DateTimeInterval is ONLY for TESTING AvailabilityRecyclerView
 */
public class DateTimeInterval {
    private String timeSlot;

    public DateTimeInterval(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getTimeSlot(){
        return this.timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }


    public String toString() {
        return timeSlot;
    }

}
