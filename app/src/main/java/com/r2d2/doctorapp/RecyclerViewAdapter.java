package com.r2d2.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private ArrayList<Doctor.Profile> doctors;
    private String specFilter;
    private Patient.Profile currentPatient;
    private Context context;

    public RecyclerViewAdapter(Context context, ArrayList<Doctor.Profile> doctors, String specFilter, Patient.Profile currentPatient) {
        this.doctors = doctors;
        this.specFilter = specFilter;
        this.currentPatient = currentPatient;
        this.context = context;
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
                Toast.makeText(context, formatDoctorName(doctor), Toast.LENGTH_SHORT).show();

                // Go to the next activity upon selecting a doctor
                Intent intent = new Intent(v.getContext(), AvailabilityActivity.class);
                intent.putExtra(AvailabilityActivity.EXTRA_DOCTOR_PROFILE, doctor);
                intent.putExtra(AvailabilityActivity.EXTRA_PATIENT_PROFILE, currentPatient);
                // intent.putExtra(ExActivity.EXTRA_SPECFILTER, specFilter);
                v.getContext().startActivity(intent);
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
