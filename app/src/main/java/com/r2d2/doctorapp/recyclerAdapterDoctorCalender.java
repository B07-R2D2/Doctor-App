package com.r2d2.doctorapp;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapterDoctorCalender extends RecyclerView.Adapter<recyclerAdapterDoctorCalender.MyViewHolder> {

    private final ArrayList<Appointment> apptlists;
    private ArrayList<Patient.Profile> ppList;
    private ArrayList<String> names;

    public recyclerAdapterDoctorCalender(ArrayList<Appointment> apptlists, ArrayList<Patient.Profile> ppList, ArrayList<String> names){
        this.apptlists = apptlists;
        this.ppList = ppList;
        this.names = names;
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
//            button.setOnClickListener(v -> {
//                Intent intent = new Intent(view.getContext(), PatientProfileActivity.class);
//                intent.putExtra(PatientProfileActivity.EXTRA_PATIENT_PROFILE, patient);
//                v.getContext().startActivity(intent);
//            });
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
        if(apptlists.size()==0){
            holder.text.setText("No schedule today!!");
            return;
        }

        String name = apptlists.get(position).getPatientName();
        String time = apptlists.get(position).toString();   // appointment.toString() returns the date and time
        if(name == ""){
            holder.text.setText(time + " is not booked");
        }
        else{
            holder.text.setText(name + " @ "+ time);
            holder.patient = ppList.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return apptlists.size();
    }
}
