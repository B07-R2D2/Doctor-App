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
import java.util.stream.Collectors;

/**
 * Adapter for MyViewHolder
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    // stores the list of the timeslots we want to display
    private AvailabilitySchedule schedule;
    // i think if schedule.timeSlot is ArrayList then we don't need this
    private ArrayList<DateTimeInterval> timeSlotList;

//    public RecyclerAdapter(ArrayList<DateTimeInterval> timeSlotList) {
//        this.timeSlotList = timeSlotList;
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerAdapter(AvailabilitySchedule schedule) {
        this.schedule = schedule;
        // convert hashset to array, might need sorting
        this.timeSlotList = new ArrayList<>(schedule.timeSlots());
        Collections.sort(this.timeSlotList);
    }

    // represents a recycler item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeSlotTxt;
        private Button button;
        // new
        DateTimeInterval slot;

        public MyViewHolder(final View view) {
            super(view);
            timeSlotTxt = view.findViewById(R.id.time_slot);
            button = view.findViewById(R.id.book_button);
            // initialized slot at onBindViewHolder
            button.setOnClickListener(new View.OnClickListener() {
                /**
                 * the action when we click the button
                 * @param v
                 */
                @Override
                public void onClick(View v) {
                    if (button.getText().equals("Booked")) {
                        button.setText("Book");
                        // do modifications here
                        schedule.addTimeSlot(slot);
                        // also needs to add from arraylist
                        timeSlotList.add(slot);
                    } else {
                        button.setText("Booked");
                        // remove from timeslots  and arraylist
                        schedule.removeTimeSlot(slot);
                        timeSlotList.remove(slot);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DateTimeInterval timeSlot = timeSlotList.get(position);
        holder.timeSlotTxt.setText(timeSlot.toString());
        // new
        // set the slot to be the current timeslot
        holder.slot = timeSlot;
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }
}