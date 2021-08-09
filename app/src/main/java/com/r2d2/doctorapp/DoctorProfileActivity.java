package com.r2d2.doctorapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorProfileActivity extends AppCompatActivity {
    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.DoctorProfileActivity.extra_doctor_profile";
    private Button deleteButton;
    private Button registerListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        Intent intent = getIntent();
        Doctor.Profile doctorProfile = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);

        registerListButton = (Button) findViewById(R.id.registerListButton);
        registerListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DoctorProfileActivity", "clicked past patients button");
                Intent intent = new Intent(v.getContext(), RegisterListActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        deleteButton = (Button) findViewById(R.id.doctorDeleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DoctorProfileActivity", "clicked delete button");
                showConfirmation(doctorProfile);
            }
        });
    }

    private void showConfirmation(Doctor.Profile doctorProfile){
        Log.d("DoctorProfileActivity", "Want to delete: " + doctorProfile.getUsername() + ", " + doctorProfile.getSpecialization());
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
                deleteDoctor(doctorProfile);
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

    private void deleteDoctor(Doctor.Profile doctorProfile){
        String username = doctorProfile.getUsername();
        String spec = doctorProfile.getSpecialization().toLowerCase();
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

        //Send user back to log in page.
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}