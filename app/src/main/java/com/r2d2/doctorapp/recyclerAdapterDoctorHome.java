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

import java.util.List;
import java.util.Map;

public class recyclerAdapterDoctorHome extends RecyclerView.Adapter<recyclerAdapterDoctorHome.MyViewHolder> {

    private final List<Appointment> apptlists;
    private Map<String, Patient.Profile> patientProfiles;
    private String fromclass;

    public recyclerAdapterDoctorHome(List<Appointment> apptlists, Map<String, Patient.Profile> patientProfiles, String fromclass){
        this.apptlists = apptlists;
        this.patientProfiles = patientProfiles;
        this.fromclass = fromclass;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;      // patient name, time
        private Button button;      // button that says "patient"
        Patient.Profile patient;

        public MyViewHolder(final View view){
            super(view);
            text = view.findViewById(R.id.textView14);
            button = view.findViewById(R.id.button2);

//             when clicked send to patient profile page
            button.setOnClickListener(v -> {
                if (patient != null) {
                    Intent intent = new Intent(view.getContext(), PatientProfileActivity.class);
                    intent.putExtra(PatientProfileActivity.EXTRA_PATIENT_PROFILE, patient);
                    v.getContext().startActivity(intent);
                }
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

        Log.i("info", "getclass: " + this.getClass());

        // if from DoctorHome class
        if(fromclass.equals("DoctorHome")){
            if(checkAppointmentListIsEmpty(apptlists)){
                holder.text.setText("No appointments booked today!");
                holder.button.setVisibility(View.INVISIBLE);
                return;
            }

            Appointment appt = apptlists.get(position);
            if(!appt.getPatientName().isEmpty())
                setUpHolderWithPatient(holder, appt);
        }else if(fromclass.equals("DoctorCalendar")){ // if from DoctorCalendar class
            Appointment appt = apptlists.get(position);
            if(appt.getPatientName().isEmpty())
                setUpHolderWithoutPatient(holder, appt);
            else
                setUpHolderWithPatient(holder, appt);
        }
    }

    private void setUpHolderWithPatient(MyViewHolder holder, Appointment appt) {
        Patient.Profile patientProfile = patientProfiles.get(appt.getPatientName());
        String patientFullName = (patientProfile == null || patientProfile.getSin() == 0) ?
                "Deleted patient" :
                patientProfile.getFirstName() + " " + patientProfile.getLastName();
        holder.text.setText(patientFullName + " @ "+ appt);
        holder.button.setVisibility(View.VISIBLE);
        holder.patient = patientProfile;
    }

    private void setUpHolderWithoutPatient(MyViewHolder holder, Appointment appt) {
        holder.text.setText(appt + " is not booked");
        holder.button.setVisibility(View.INVISIBLE);
    }

    // checks if apptlists is empty
    public boolean checkAppointmentListIsEmpty(List<Appointment> apptlists){
        int count = 0;
        for (Appointment appointment :apptlists){
            if (appointment.getPatientName().isEmpty())
                count++;
        }
        if (count == apptlists.size())
            return true;
        return false;
    }

    @Override
    public int getItemCount() {
        if(checkAppointmentListIsEmpty(apptlists) && fromclass.equals("DoctorHome"))
            return 1;
        return apptlists.size();
    }
}

