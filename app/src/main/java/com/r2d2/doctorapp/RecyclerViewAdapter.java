package com.r2d2.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{


    private ArrayList<Doctor> doctors;
    private Context context;
   // private Patient patient;

    public RecyclerViewAdapter(Context context, ArrayList<Doctor> doctors) {
        this.doctors = doctors;
        this.context = context;
//        this.patient = patient;
//        Log.i("Patient", "received patient in recycler view adapter: " + patient.getUsername());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.doctorName.setText(doctors.get(position).getFirstName() + " " + doctors.get(position).getLastName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("clicked on", doctors.get(position).getFirstName() + " " + doctors.get(position).getLastName());
                Toast.makeText(context, doctors.get(position).getFirstName() + " " + doctors.get(position).getLastName(), Toast.LENGTH_SHORT).show();

                // Go to the next activity upon selecting a doctor
                Intent intent = new Intent(v.getContext(), ExActivity.class);
                // intent.putExtra("Doctor",(Parcelable) doctors.get(position));
                // intent.putExtra("Doctor", doctors.get(position));
                // Log.i("Patient", "patient in recycler view adapter : " + patient.getUsername());
                // intent.putExtra(DoctorHistoryActivity.EXTRA_PATIENT, patient);
                // Log.i("Patient", "going to send: " + patient.getLastName() + " and " + doctors.get(position).getUsername());
                v.getContext().startActivity(intent);
            }
        });
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
