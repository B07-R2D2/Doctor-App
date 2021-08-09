package com.r2d2.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class PatientSignup2Activity extends AppCompatActivity {
    public final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private PatientSignup2View presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup2);
        presenter = new PatientSignup2View(this);
    }

    public void signup(View view) {
        // Do something in response to clicking Submit
        Pattern genderpattern = Pattern.compile("(male|female|other)");
        Pattern sinpattern = Pattern.compile("^[1-9]\\d*");
        Pattern datepattern = Pattern.compile("\\d{4} \\d{2} \\d{2}");
        EditText send = (EditText) findViewById(R.id.editTextTextMultiLine);
        EditText send2 = (EditText) findViewById(R.id.gender);
        EditText send3 = (EditText) findViewById(R.id.sinNumber);
        EditText send4 = (EditText) findViewById(R.id.DateInfo);
        String MedCon = send.getText().toString().trim();
        String Gender = send2.getText().toString().trim();
        String sinString = send3.getText().toString().trim();
        String dateStuff = send4.getText().toString().trim();
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
        if(dateStuff.isEmpty())
        {
            send4.setError("Please enter your date of birth in Year Month Day format");
            send4.requestFocus();
            return;
        }
        if(datepattern.matcher(dateStuff).matches() == false)
        {
            send4.setError("Please enter your date of birth in Year Month Day format");
            send4.requestFocus();
            return;
        }
        //get Date setup
        String year = dateStuff.substring(0,3);
        String month = dateStuff.substring(5,6);
        String day = dateStuff.substring(8);
        int Iyear = Integer.parseInt(year);
        int Imonth = Integer.parseInt(month);
        int Iday = Integer.parseInt(day);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Iyear, Imonth, Iday, 0, 0, 0);
        Date DOB = calendar.getTime();

        presenter.backtologin(Sin,Gender,MedCon,DOB);
    }
    public void back(View view)
    {
        presenter.back();
    }
}
