package com.r2d2.doctorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorHomePageActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = LoginActivity.givenUsername;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_page);

        Intent intent = getIntent();
        String username = intent.getStringExtra(EXTRA_USERNAME);
        Doctor doctor = new Doctor(FirebaseDatabase.getInstance(), username);

        deleteButton = (Button) findViewById(R.id.doctorDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DoctorHomePageActivity", "clicked delete button");
                showConfirmation(doctor);
            }
        });
    }

    private void showConfirmation(Doctor doctor){
        Log.d("DoctorHomePageActivity", "Want to delete: " + doctor.getProfile().getUsername() + ", " + doctor.getProfile().getSpecialization());
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.confirm_delete_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button yesButton = (Button) dialogView.findViewById(R.id.yesDeleteButton);
        final Button noButton = (Button) dialogView.findViewById(R.id.noDeleteButton);

        AlertDialog ad = dialogBuilder.create();
        ad.show();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDoctor(doctor);
                ad.dismiss();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
            }
        });
    }

    private void deleteDoctor(Doctor doctor){
        String username = doctor.getProfile().getUsername();
        String spec = doctor.getProfile().getSpecialization().toLowerCase();
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference("Doctors").child(username);
        DatabaseReference specRef = FirebaseDatabase.getInstance().getReference("DoctorsSpecial").child(spec).child(username);

        loginRef.removeValue();
        specRef.removeValue();
        // TODO: figure out why it does the following
        // if there are multiple doctors in the same specialization branch, it deletes one doctor only
        // if there is only one doctor, it deletes the doctor and the specialization branch
        // not sure why it does this, but it sure makes the job a lot easier

        Toast.makeText(this, "Deleting " + username, Toast.LENGTH_SHORT).show();

        // TODO: Cancel all appointments with this doctor
    }
}