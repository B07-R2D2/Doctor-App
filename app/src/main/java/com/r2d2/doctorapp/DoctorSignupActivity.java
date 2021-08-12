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
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DoctorSignupActivity extends AppCompatActivity {
    public static final String setUSERNAME = "com.example.DoctorApp.SETUSERMESSAGE";
    public static final String setPASSWORD = "com.example.DoctorApp.SETPASSWORDMESSAGE";
    public static final String setFIRSTNAME = "com.example.DoctorApp.SETFIRSTNAMEMESSAGE";
    public static final String setLASTNAME = "com.example.DoctorApp.SETLASTNAMEMESSAGE";
    public static final String setGENDER = "com.example.DoctorApp.SETGENDERMESSAGE";

    List<String> specs = new ArrayList<>();
    EditText addSpecText;
    Button addSpecButton;
    TextView addedSpecText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);

        // Stuff that allows adding multiple specializations
        addSpecText = (EditText) findViewById(R.id.addSpecText);
        addSpecButton = (Button) findViewById(R.id.addSpecButton);
        addedSpecText = (TextView) findViewById(R.id.addedSpecText);

        addSpecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newSpec = addSpecText.getText().toString().trim().toLowerCase();
                if (!specs.contains(newSpec)) {
                    specs.add(newSpec);
                }
                addedSpecText.setText(getString(R.string.doctor_signup_label_added_specializations) + specs.stream().map(Object::toString).collect(Collectors.joining(", ")));
                addSpecText.getText().clear();
            }
        });
    }

    public void sendMessage(View view){
        EditText usernameField = (EditText) findViewById(R.id.field_username);
        EditText passwordField = (EditText) findViewById(R.id.field_password);
        EditText firstNameField = (EditText) findViewById(R.id.field_first_name);
        EditText lastNameField = (EditText) findViewById(R.id.field_last_name);
        EditText genderField = (EditText) findViewById(R.id.field_gender);
        EditText uniField = (EditText) findViewById(R.id.field_uni);
        EditText doctorIDField = (EditText) findViewById(R.id.field_doctor_id);
        EditText sinField = (EditText) findViewById(R.id.field_sin);
        EditText bioField = (EditText) findViewById(R.id.field_doctor_bio);
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String firstname = firstNameField.getText().toString().trim();
        String lastname = lastNameField.getText().toString().trim();
        String gender = genderField.getText().toString().trim().toLowerCase();
        String uni = uniField.getText().toString().trim();
        String dId = doctorIDField.getText().toString().trim();
        String sin1 = sinField.getText().toString().trim();
        String bio = bioField.getText().toString().trim();

        Pattern genderpattern = Pattern.compile("(male|female|other)");
        Pattern numberPattern = Pattern.compile("^[1-9]\\d*");
        if(username.isEmpty()){
            usernameField.setError("Please enter username");
            usernameField.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordField.setError("Please enter password");
            passwordField.requestFocus();
            return;
        }
        if(firstname.isEmpty()){
            firstNameField.setError("Please enter your first name");
            firstNameField.requestFocus();
            return;
        }
        if(lastname.isEmpty()){
            lastNameField.setError("Please enter your last name");
            lastNameField.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            genderField.setError("Please enter your gender");
            genderField.requestFocus();
            return;
        }
        else if(genderpattern.matcher(gender).matches() == false){
            genderField.setError("Please enter Male, Female, or Other");
            genderField.requestFocus();
            return;
        }
        if(uni.isEmpty()){
            uniField.setError("Please enter where you attended university");
            uniField.requestFocus();
            return;
        }
        if(specs.isEmpty()){
            addSpecText.setError("Please add at least one specialization");
            addSpecText.requestFocus();
            return;
        }
        if(dId.isEmpty()){
            doctorIDField.setError("Please enter your Doctor ID Number");
            doctorIDField.requestFocus();
            return;
        }
        else if(!numberPattern.matcher(dId).matches()){
            doctorIDField.setError("Doctor ID requires all digits and cannot start with zero");
            doctorIDField.requestFocus();
            return;
        }
        if(sin1.isEmpty()){
            sinField.setError("Please enter your Health Number");
            sinField.requestFocus();
            return;
        }
        else if(!numberPattern.matcher(sin1).matches()){
            sinField.setError("Health Number requires all digits and cannot start with zero");
            sinField.requestFocus();
            return;
        }
        if(bio.isEmpty()){
            bioField.setError("Please enter a short bio about yourself");
            bioField.requestFocus();
            return;
        }

        int doctorId = Integer.parseInt(dId);
        int sin = Integer.parseInt(sin1);

        new Doctor(
            FirebaseDatabase.getInstance(),
            new Doctor.Profile(firstname, lastname, username, password, gender, sin, Doctor.makeOneWeekOfAppointments(username), bio, uni, doctorId, new ArrayList<>(specs))
        );

        // Send user back to log in page.
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }

    public void backButton(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
    }
}
