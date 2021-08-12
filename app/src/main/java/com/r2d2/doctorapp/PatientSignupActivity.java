package com.r2d2.doctorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class PatientSignupActivity extends AppCompatActivity {
    public static final String setUSERNAME = "com.example.DoctorApp.SETUSERMESSAGE";
    public static final String setPASSWORD = "com.example.DoctorApp.SETPASSWORDMESSAGE";
    public static final String setFIRSTNAME = "com.example.DoctorApp.SETFIRSTNAMEMESSAGE";
    public static final String setLASTNAME = "com.example.DoctorApp.SETLASTNAMEMESSAGE";
    private PatientSignupPresenter presenter;

    private DatePickerFragment datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        presenter = new PatientSignupPresenter(this);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        public int year, month, day;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            if (year == 0) {
                final Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
            }

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int y, int m, int d) {
            // Do something with the date chosen by the user
            year = y;
            month = m;
            day = d;
        }
    }

    public void showDatePickerDialog(View view) {
        if (datePicker == null) datePicker = new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(), "datePicker");
    }

    public void signUp(View view) {
        // Do something in response to clicking Submit

        EditText usernameField = (EditText) findViewById(R.id.field_username);
        EditText passwordField = (EditText) findViewById(R.id.field_password);
        EditText firstNameField = (EditText) findViewById(R.id.field_first_name);
        EditText lastNameField = (EditText) findViewById(R.id.field_last_name);
        EditText genderField = (EditText) findViewById(R.id.field_gender);
        EditText sinField = (EditText) findViewById(R.id.field_sin);
        EditText medicalConditionField = (EditText) findViewById(R.id.field_medical_condition);

        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String firstName = firstNameField.getText().toString().trim();
        String lastName = lastNameField.getText().toString().trim();
        String medicalCondition = medicalConditionField.getText().toString().trim();
        String gender = genderField.getText().toString().trim().toLowerCase();
        String sin = sinField.getText().toString().trim();

        Pattern genderPattern = Pattern.compile("(male|female|other)");
        Pattern sinPattern = Pattern.compile("^[1-9]\\d*");

        if(username.isEmpty()){
            usernameField.setError("Please enter a username");
            usernameField.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordField.setError("Please enter a password");
            passwordField.requestFocus();
            return;
        }
        if(firstName.isEmpty()){
            firstNameField.setError("Please enter your first name");
            firstNameField.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            lastNameField.setError("Please enter your last name");
            lastNameField.requestFocus();
            return;
        }
        if(medicalCondition.isEmpty()){
            medicalConditionField.setError("Please enter your medical condition");
            medicalConditionField.requestFocus();
            return;
        }
        if(sin.isEmpty()){
            sinField.setError("Please enter your Health Number");
            sinField.requestFocus();
            return;
        }
        else if(!sinPattern.matcher(sin).matches()){
            sinField.setError("Health Number requires all digits and cannot start with zero");
            sinField.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            genderField.setError("Please enter your gender: male, female, or other");
            genderField.requestFocus();
            return;
        }
        else if(!genderPattern.matcher(gender).matches())
        {
            genderField.setError("Please enter your gender: male, female, or other");
            genderField.requestFocus();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.year, datePicker.month, datePicker.day, 0, 0, 0);
        long dateOfBirth = calendar.getTimeInMillis();

        presenter.signUp(username, password, firstName, lastName, Integer.parseInt(sin), gender, medicalCondition, dateOfBirth);
    }

    public void back(View view)
    {
        presenter.back();
    }

}