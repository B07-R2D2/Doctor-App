package com.r2d2.doctorapp;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

/** An interval of time on a certain day. */
public final class DateTimeInterval {

    private Date start, end;

    public DateTimeInterval(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    /** The start date/time of the interval. */
    public Date start() {
        return start;
    }
    /** The end date/time of the interval. */
    public Date end() {
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
}
