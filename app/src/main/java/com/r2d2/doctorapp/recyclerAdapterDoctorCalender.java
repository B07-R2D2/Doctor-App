package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapterDoctorCalender extends RecyclerView.Adapter<recyclerAdapterDoctorCalender.MyViewHolder> {

    private final ArrayList<Appointment> appointments;
    private ArrayList<Patient.Profile> patientProfiles;

    public recyclerAdapterDoctorCalender(ArrayList<Appointment> appointments, ArrayList<Patient.Profile> patientProfiles){
        this.appointments = appointments;
        this.patientProfiles = patientProfiles;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;      // patient name, time
        private Button button;      // button that says "patient"
        Patient.Profile patient;

        public MyViewHolder(final View view){
            super(view);
            text = view.findViewById(R.id.textView15);
            button = view.findViewById(R.id.button3);

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
    public recyclerAdapterDoctorCalender.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_for_doctor_calender, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapterDoctorCalender.MyViewHolder holder, int position) {
        if(appointments.size()==0){
            holder.text.setText("No schedule today!!");
            return;
        }

        Appointment appt = appointments.get(position);
        String time = appt.toString();   // appointment.toString() returns the date and time
        if(appt.getPatientName().isEmpty()){
            holder.text.setText(time + " is not booked");
            holder.button.setVisibility(View.INVISIBLE);
        }
        else{
            Patient.Profile patientProfile = patientProfiles.get(position);
            String name = patientProfile.getFirstName() + " " + patientProfile.getLastName();
            holder.text.setText(name + " @ "+ time);
            holder.patient = patientProfiles.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}
