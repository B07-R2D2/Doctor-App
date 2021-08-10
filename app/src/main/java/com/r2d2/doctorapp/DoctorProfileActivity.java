package com.r2d2.doctorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorProfileActivity extends AppCompatActivity {
    public static final String EXTRA_DOCTOR_PROFILE = "com.r2d2.DoctorApp.DoctorProfileActivity.extra_doctor_profile";
    private ArrayList<Patient.Profile> patientList = new ArrayList<>();
    private Button deleteButton;
    private TextView docName;
    private TextView docGender;
    private TextView docSpec;
    private TextView docBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        Intent intent = getIntent();
        Doctor.Profile doctorProfile = (Doctor.Profile) intent.getSerializableExtra(EXTRA_DOCTOR_PROFILE);

        // Setting up TextViews for the doctor info
        docName = findViewById(R.id.doctorProfileName);
        docGender = findViewById(R.id.doctorProfileGender);
        docSpec = findViewById(R.id.doctorProfileSpecialization);
        docBio = findViewById(R.id.doctorProfileBio);
        docName.setText(doctorProfile.getFirstName() + " " + doctorProfile.getLastName());
        docGender.setText(doctorProfile.getGender());
        docSpec.setText(doctorProfile.getSpecializations().stream().map(Object::toString).collect(Collectors.joining(", ")));
        docBio.setText(doctorProfile.getBio());

        // Getting past patients and initializing the RecyclerView
        List<String> pastPatientUsernames = doctorProfile.getPastPatients();
        for (String user : pastPatientUsernames){
            Patient patient = new Patient(FirebaseDatabase.getInstance(), user);
            patient.addOneTimeObserver(()-> {
                patientList.add(patient.getProfile());
                initRecyclerView();
            });
        }

        // Button to delete the doctor's account
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
        Log.d("DoctorProfileActivity", "Want to delete: " + doctorProfile.getUsername() + ", " + doctorProfile.getSpecializations().toString());
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
        List<String> specializations = doctorProfile.getSpecializations();

        // Remove doctor from Doctors
        DatabaseReference loginRef = FirebaseDatabase.getInstance().getReference("Doctors").child(username);
        loginRef.removeValue();

        // Remove doctor from DoctorsSpecial
        for (String spec : specializations) {
            DatabaseReference specRef = FirebaseDatabase.getInstance().getReference("DoctorsSpecial").child(spec).child(username);
            specRef.removeValue();
        }

        Toast.makeText(this, "Deleting " + username, Toast.LENGTH_SHORT).show();

        // TODO: Cancel all appointments with this doctor

        //Send user back to log in page.
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.registerListRecycler);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2(this, patientList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
