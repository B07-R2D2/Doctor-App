package com.r2d2.doctorapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<DateTimeInterval> timeSlotList;

    public RecyclerAdapter(ArrayList<DateTimeInterval> timeSlotList) {
        this.timeSlotList = timeSlotList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView timeSlotTxt;
        public MyViewHolder(final View view) {
            super(view);
            timeSlotTxt = view.findViewById(R.id.time_slot);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String displayTime = timeSlotList.get(position).toString();
        holder.timeSlotTxt.setText(displayTime);
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }
}