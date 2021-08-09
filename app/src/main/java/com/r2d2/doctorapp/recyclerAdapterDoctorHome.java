package com.r2d2.doctorapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

public class recyclerAdapterDoctorHome extends RecyclerView.Adapter<recyclerAdapterDoctorHome.MyViewHolder> {

    private final ArrayList<Appointment> apptlists;

    public recyclerAdapterDoctorHome(ArrayList<Appointment> apptlists){
        this.apptlists = apptlists;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text;
        private Button button;

        public MyViewHolder(final View view){
            super(view);
            text = view.findViewById(R.id.textView14);
            button = view.findViewById(R.id.button2);

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
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
        String name = apptlists.get(position).getPatientName;
        Date time = new Date(apptlists.get(position).getTimeStamp());

        holder.text.setText(name + "@"+ time;
    }

    @Override
    public int getItemCount() {
        return apptlists.size();
    }
}
