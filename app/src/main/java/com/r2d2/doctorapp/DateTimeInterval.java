package com.r2d2.doctorapp;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/** An interval of time on a certain day. */
public final class DateTimeInterval implements Serializable, Comparable<DateTimeInterval> {

    private Date start, end;
//    Patient patient;      // might be helpful for "next appointment feature, will comment out for now

    public DateTimeInterval() {
    }
    public DateTimeInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    /** The start date/time of the interval. */
    public Date getStart() {
        return start;
    }
    /** The end date/time of the interval. */
    public Date getEnd() {
        return end;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null || obj.getClass() != getClass()) return false;
        DateTimeInterval other = (DateTimeInterval) obj;
        return other.start.equals(start) && other.end.equals(end);
    }
    @Override
    public int hashCode() {
        return start.hashCode() ^ end.hashCode();
    }
    @Override
    public String toString() {
        return start + "–" + end;
    }
    // might be helpful for "next appointment"
//    public String toString() {
//        return start + "–" + end + " " + patient;
//    }

    @Override
    public int compareTo(DateTimeInterval o) {
        int c = this.start.compareTo(o.start);
        if (c == 0)
            return this.end.compareTo(o.end);
        return c;
    }
}
