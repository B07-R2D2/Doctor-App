package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapterDoctorHome extends RecyclerView.Adapter<recyclerAdapterDoctorHome.MyViewHolder> {

    private final List<Appointment> apptlists;
    private List<Patient.Profile> ppList;

    public recyclerAdapterDoctorHome(List<Appointment> apptlists, List<Patient.Profile> ppList){
        this.apptlists = apptlists;
        this.ppList = ppList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;      // patient name, time
        private Button button;      // button that says "patient"
        Patient.Profile patient;

        public MyViewHolder(final View view){
            super(view);
            text = view.findViewById(R.id.textView14);
            button = view.findViewById(R.id.button2);

//             when clicked send to patient profile page
            button.setOnClickListener(v -> {
                Intent intent = new Intent(view.getContext(), PatientProfileActivity.class);
                intent.putExtra(PatientProfileActivity.EXTRA_PATIENT_PROFILE, patient);
                v.getContext().startActivity(intent);
            });
        }
    }

    @NonNull
    @Override
    public recyclerAdapterDoctorHome.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterDoctorHome.MyViewHolder holder, int position) {
        if(apptlists.size()==0){
            holder.text.setText("No schedule today!!");
            return;
        }

        String name = apptlists.get(position).getPatientName();
        String time = apptlists.get(position).toString();   // appointment.toString() returns the date and time
        holder.text.setText(name + " @ "+ time);
        holder.patient = ppList.get(position);

    }

    @Override
    public int getItemCount() {
        return apptlists.size();
    }

}

