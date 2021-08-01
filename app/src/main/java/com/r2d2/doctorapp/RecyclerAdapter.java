package com.r2d2.doctorapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * adapter for Recycler view
 * NOTE: REQUIRES DateTimeInterval to be Comparable
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    // the schedule to be displayed
    private AvailabilitySchedule schedule;
    // since we need to get the index for each time slot and sort them (schedule.timeSlots is type Set)
    private ArrayList<DateTimeInterval> timeSlotList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerAdapter(AvailabilitySchedule schedule) {
        this.schedule = schedule;

        // convert hashset to array and sort to get correct order
        this.timeSlotList = new ArrayList<>(schedule.timeSlots());
        Collections.sort(this.timeSlotList);
    }

    // represents a recycler item
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView timeSlotTxt;
        private Button button;
        // since we want to add and/or remove timeslots from schedule
        private DateTimeInterval slot;

        public RecyclerViewHolder(final View view) {
            super(view);
            timeSlotTxt = view.findViewById(R.id.time_slot);
            button = view.findViewById(R.id.book_button);
            // initialized slot at onBindViewHolder
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (button.getText().equals("Booked")) {    // cancel the appointment by clicking again!
                        button.setText("Book");                 // I tried to use strings from resources but it didn't work
                                                                // plz tell me if you know how
                        // updates schedule and timeSlotList
                        schedule.addTimeSlot(slot);
                        timeSlotList.add(slot);
                    } else {
                        button.setText("Booked");

                        // updates schedule and timeSlotList
                        schedule.removeTimeSlot(slot);
                        timeSlotList.remove(slot);
                    }
                }
            });
        }
    }

    // creates a new RecyclerViewHolder
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    // when binding the view holder to a timeSlot
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        DateTimeInterval timeSlot = timeSlotList.get(position);             // current timeslot to be binded
        holder.timeSlotTxt.setText(timeSlot.toString());
        holder.slot = timeSlot;
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }
}