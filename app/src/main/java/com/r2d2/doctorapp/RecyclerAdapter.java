package com.r2d2.doctorapp;

import android.os.Build;
import android.util.Log;
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

/**
 * adapter for Recycler view
 * NOTE: REQUIRES DateTimeInterval to be Comparable
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


    private Doctor doctor;
    private Patient patient;

    // we still need this even if schecule.timeSlots is type ArrayList because otherwise the
    // program will crash if the list is too long and we have booked items out of view (when scrolling)
    private ArrayList<Appointment> appointmentList;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public RecyclerAdapter(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
        Log.i("Patient", "received patient in recycler adapter");
        // convert hashset to array and sort to get correct order
        this.appointmentList = new ArrayList<>(doctor.getFreeAppointments());
        Log.i("Patient", "received appointment list");
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
                @Override
                public void onClick(View v) {
                    AppointmentManager man = new AppointmentManager(doctor, patient, appointment.getTimeSlot());
                    if (button.getText().equals("Booked")) {    // cancel the appointment by clicking again!
                        button.setText("Book");                 // I tried to use strings from resources but it didn't work
                                                                // plz tell me if you know how
                        // updates schedule and timeSlotList
                        man.removeAppointmentFromPatient();
                        // Dont need to update timeSlotList, because it's dependent on schedule
                    } else {
                        button.setText("Booked");

                        // updates schedule and timeSlotList
                        man.addAppointmentToPatient();
                        // Dont need to update timeSlotList, because it's dependent on schedule
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
        Appointment appointment = appointmentList.get(position);             // current timeslot to be binded
        holder.timeSlotTxt.setText(appointment.toString());
        holder.appointment = appointment;
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }
}