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

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder>{


    private ArrayList<Patient> patients;
    private Context context;

    public RecyclerViewAdapter2(Context context, ArrayList<Patient> patients) {
        this.patients = patients;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.register_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.patientName.setText(patients.get(position).getProfile().getFirstName() + " " + patients.get(position).getProfile().getLastName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("clicked on", patients.get(position).getProfile().getFirstName() + " " + patients.get(position).getProfile().getLastName());
                Toast.makeText(context, patients.get(position).getProfile().getFirstName() + " " + patients.get(position).getProfile().getLastName(), Toast.LENGTH_SHORT).show();

                // Go to the next activity upon selecting a doctor
                Intent intent = new Intent(v.getContext(), ExActivity.class);
                //intent.putExtra("test", (Parcelable) doctors.get(position));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView patientName;
        ConstraintLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);
            patientName = itemView.findViewById(R.id.patient_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
