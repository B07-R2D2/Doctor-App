package com.r2d2.doctorapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private List<Doctor.Profile> doctors;
    private Context context;
    private Consumer<Doctor.Profile> onRowClick;

    public RecyclerViewAdapter(Context context, List<Doctor.Profile> doctors, Consumer<Doctor.Profile> onRowClick) {
        this.doctors = doctors;
        this.context = context;
        this.onRowClick = onRowClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Doctor.Profile doctor = doctors.get(position);
        Log.i("bound view holder", formatDoctorName(doctor));
        holder.doctorName.setText(formatDoctorName(doctor));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doctor.Profile doctor = doctors.get(holder.getAdapterPosition());
                Log.i("clicked on", formatDoctorName(doctor));
                // Run user-provided handler.
                onRowClick.accept(doctor);
            }
        });
    }

    private static String formatDoctorName(Doctor.Profile doctor) {
        return doctor.getFirstName() + " " + doctor.getLastName();
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView doctorName;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            doctorName = itemView.findViewById(R.id.doctor_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
