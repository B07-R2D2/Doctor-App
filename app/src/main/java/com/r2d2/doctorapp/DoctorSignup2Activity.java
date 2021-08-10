package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DoctorSignup2Activity extends AppCompatActivity {

    List<String> specs = new ArrayList<>();
    EditText addSpecText;
    Button addSpecButton;
    TextView addedSpecText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup2);

        // Stuff that allows adding multiple specializations
        addSpecText = (EditText) findViewById(R.id.addSpecText);
        addSpecButton = (Button) findViewById(R.id.addSpecButton);
        addedSpecText = (TextView) findViewById(R.id.addedSpecText);

        addSpecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSpec = addSpecText.getText().toString().trim();
                specs.add(newSpec);
                addedSpecText.setText("Added: " + specs.stream().map(Object::toString).collect(Collectors.joining(", ")));
                addSpecText.getText().clear();
            }
        });

    }

    public void submitButton(View view){
        Intent da1 = getIntent();
        String username = da1.getStringExtra(DoctorSignupActivity.setUSERNAME);
        String password = da1.getStringExtra(DoctorSignupActivity.setPASSWORD);
        String firstname = da1.getStringExtra(DoctorSignupActivity.setFIRSTNAME);
        String lastname = da1.getStringExtra(DoctorSignupActivity.setLASTNAME);
        String gender = da1.getStringExtra(DoctorSignupActivity.setGENDER);

        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText editText3 = (EditText) findViewById(R.id.editTextTextPersonName8);
        EditText editText4 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText5 = (EditText) findViewById(R.id.editTextTextPersonName10);
        String uni = editText1.getText().toString().trim();
        String dId = editText3.getText().toString().trim();
        String sin1 = editText4.getText().toString().trim();
        String bio = editText5.getText().toString().trim();

        Pattern pattern = Pattern.compile("^[1-9]\\d*");
        if(uni.isEmpty()){
            editText1.setError("Please Enter where you attend university");
            editText1.requestFocus();
            return;
        }
        if(specs.isEmpty()){
            addSpecText.setError("Please Enter your specialization");
            addSpecText.requestFocus();
            return;
        }
        if(dId.isEmpty()){
            editText3.setError("Please Enter your Doctor's Id Number");
            editText3.requestFocus();
            return;
        }
        else if(pattern.matcher(dId).matches() == false){
            editText3.setError("Doctor's Id requires all digits and cannot start with zero");
            editText3.requestFocus();
            return;
        }
        if(sin1.isEmpty()){
            editText4.setError("Please Enter your SIN Number");
            editText4.requestFocus();
            return;
        }
        else if(pattern.matcher(sin1).matches() == false){
            editText4.setError("SIN Number requires all digits and cannot start with zero");
            editText4.requestFocus();
            return;
        }
        if(bio.isEmpty()){
            editText5.setError("Please Enter a a short bio about yourself");
            editText5.requestFocus();
            return;
        }

        int doctorId = new Integer(dId).intValue();
        int sin = new Integer(sin1).intValue();

        Doctor doctor = new Doctor(
            FirebaseDatabase.getInstance(),
            new Doctor.Profile(firstname, lastname, username, password, gender, sin, Doctor.makeOneWeekOfAppointments(username), bio, uni, doctorId, new ArrayList<>(specs))
        );

        //Send user back to log in page.
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void backButton1(View view){
        Intent intent = new Intent(this,DoctorSignupActivity.class);
        startActivity(intent);
    }
}