
package com.r2d2.doctorapp;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * adapter for Recycler view
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    // the schedule to be displayed
    private Doctor.Profile doctor;
    private Patient.Profile patient;

    // we still need this even if schecule.timeSlots is type ArrayList because otherwise the
    // program will crash if the list is too long and we have booked items out of view (when scrolling)
    private ArrayList<Appointment> timeSlotList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerAdapter(Doctor.Profile doctor, Patient.Profile patient) {
        this.doctor = doctor;
        this.patient = patient;

        this.timeSlotList = new ArrayList<>();
        List<Appointment> appointments = doctor.getAppointments();
        Collections.sort(appointments);
        for (Appointment a : appointments) {
            if (a.getPatientName().equals("")) {
                this.timeSlotList.add(a);
            }
        }

    }

    // represents a recycler item
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView timeSlotTxt;
        private Button button;
        // since we want to add and/or remove timeslots from schedule
        private Appointment appointment;

        public RecyclerViewHolder(final View view) {
            super(view);
            timeSlotTxt = view.findViewById(R.id.time_slot);
            button = view.findViewById(R.id.book_button);
            // initialized slot at onBindViewHolder
            button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    AppointmentManager man = new AppointmentManager(doctor, patient, appointment.getTimeStamp());
                    if (button.getText().equals("Booked")) {    // cancel the appointment by clicking again!
                        button.setText("Book");                 // I tried to use strings from resources but it didn't work
                                                                // plz tell me if you know how
                        man.removeAppointmentFromPatient();
                        // Dont need to update timeSlotList, because it's dependent on schedule
                        //timeSlotList.add(slot);
                    } else {
                        button.setText("Booked");
                        man.addAppointmentToPatient();
                        // Dont need to update timeSlotList, because it's dependent on doctor
                        //timeSlotList.remove(slot);
                    }
                }
            });
        }
    }

    // creates a new RecyclerViewHolder
    @NonNull
    @Override
    public RecyclerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    // when binding the view holder to a timeSlot
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Appointment app = timeSlotList.get(position);             // current timeslot to be binded
        holder.timeSlotTxt.setText(app.toString());
        holder.appointment = app;
    }

    @Override
    public int getItemCount() {
        return timeSlotList.size();
    }
}
