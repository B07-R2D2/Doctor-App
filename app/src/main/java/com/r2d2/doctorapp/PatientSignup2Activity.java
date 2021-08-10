package com.r2d2.doctorapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class PatientSignup2Activity extends AppCompatActivity {
    public final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public static int Iyear,Imonth,Iday;
    private PatientSignup2View presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
        presenter = new PatientSignup2View(this);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            PatientSignup2Activity.Iyear = year;
            PatientSignup2Activity.Imonth = month;
            PatientSignup2Activity.Iday = day;
        }
    }

    public void signup(View view) {
        // Do something in response to clicking Submit
        Pattern genderpattern = Pattern.compile("(male|female|other)");
        Pattern sinpattern = Pattern.compile("^[1-9]\\d*");
        Pattern datepattern = Pattern.compile("\\d{4} \\d{2} \\d{2}");
        EditText send = (EditText) findViewById(R.id.editTextTextMultiLine);
        EditText send2 = (EditText) findViewById(R.id.gender);
        EditText send3 = (EditText) findViewById(R.id.sinNumber);
        String MedCon = send.getText().toString().trim();
        String Gender = send2.getText().toString().trim();
        String sinString = send3.getText().toString().trim();
        int Sin = 0;
        try {
            Sin = Integer.parseInt(sinString);
        }
        catch (NumberFormatException error)
        {
            System.out.println("Could not parse " + error);
        }
        if(MedCon.isEmpty()){
            send.setError("Please enter your medical condition");
            send.requestFocus();
            return;
        }
        if(sinString.isEmpty()){
            send3.setError("Please Enter your SIN Number");
            send3.requestFocus();
            return;
        }
        else if(sinpattern.matcher(sinString).matches() == false){
            send3.setError("SIN number requires all digits and cannot start with zero");
            send3.requestFocus();
            return;
        }
        if(Gender.isEmpty()){
            send2.setError("Please enter your gender either male,female, or other");
            send2.requestFocus();
            return;
        }
        else if(genderpattern.matcher(Gender).matches() == false)
        {
            send2.setError("Please enter your gender either male,female, or other");
            send2.requestFocus();
            return;
        }
        //get Date setup
        Calendar calendar = Calendar.getInstance();
        calendar.set(Iyear, Imonth, Iday, 0, 0, 0);
        Timestamp DateOfBirth;
        DateOfBirth = new Timestamp(calendar.getTimeInMillis());

        presenter.backtologin(Sin,Gender,MedCon,DateOfBirth);
    }
    public void back(View view)
    {
        presenter.back();
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
