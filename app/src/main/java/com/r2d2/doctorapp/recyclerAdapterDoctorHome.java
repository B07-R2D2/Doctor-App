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
                    // v.getContext().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
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
//            Log.i("info", "getclass: " this.getClass());
            if(checkAppointmentListIsEmpty(apptlists)){

                holder.text.setText("No schedule today!!");
                holder.button.setVisibility(View.INVISIBLE);
                return;
            }
            String name = apptlists.get(position).getPatientName();
            String time = apptlists.get(position).toString();
            Log.i("info", "name is: " + name + "position: " + position + "applist length: " + apptlists.size());
            if(!name.isEmpty()){
                holder.text.setText(name + " @ "+ time);
                holder.patient = patientProfiles.get(name);
            }
        }else if(fromclass.equals("DoctorCalendar")){ // if from DoctorCalendar class
            Appointment appt = apptlists.get(position);
            String time = appt.toString();   // appointment.toString() returns the date and time
            if(appt.getPatientName().isEmpty()){
                holder.text.setText(time + " is not booked");
                holder.button.setVisibility(View.INVISIBLE);
            }
            else{
                Patient.Profile patientProfile = patientProfiles.get(appt.getPatientName());
                String name = (patientProfile == null) ? "Deleted patient" : patientProfile.getFirstName() + " " + patientProfile.getLastName();
                holder.text.setText(name + " @ "+ time);
                holder.button.setVisibility(View.VISIBLE);
                holder.patient = patientProfile;
            }
        }

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

