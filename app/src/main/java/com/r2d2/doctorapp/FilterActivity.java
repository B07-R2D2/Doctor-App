package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner specializationSpinner;
    private Spinner genderSpinner;
    private TextView nbrResults;
    private ArrayList<Doctor> filteredResults = new ArrayList<>();
    private String specFilter = "";
    String[] exampleSpecializations = {"Select a specialization", "Dermatologist", "Cardiologist", "Family Physician", "Pediatrician", "Psychiatrist"}; // remove after implementing database
    String[] exampleGenders = {"Select a gender", "Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        nbrResults = findViewById(R.id.resultsCountText);

        specializationSpinner = findViewById(R.id.specializationSpinner);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exampleSpecializations){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                    ViewGroup parent){
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationSpinner.setAdapter(adapter1);
        specializationSpinner.setOnItemSelectedListener(this);

        genderSpinner = findViewById(R.id.genderSpinner);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exampleGenders);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter2);
        genderSpinner.setOnItemSelectedListener(this);

        Doctor d1 = new Doctor("Doctor", "1", new Date(), 111111111, "bio 1", "uni 1", 1, "Dermatologist");
        Doctor d2 = new Doctor("Doctor", "2", new Date(), 222222222, "bio 2", "uni 2", 2, "Cardiologist");
        Doctor d3 = new Doctor("Doctor", "3", new Date(), 333333333, "bio 3", "uni 3", 3, "Family Physician");
        Doctor d4 = new Doctor("Doctor", "4", new Date(), 444444444, "bio 4", "uni 4", 4, "Pediatrician");
        Doctor d5 = new Doctor("Doctor", "5", new Date(), 555555555, "bio 5", "uni 5", 5, "Psychiatrist");
        Doctor d6 = new Doctor("Doctor", "6", new Date(), 666666666, "bio 6", "uni 6", 6, "Dermatologist");
        Doctor d7 = new Doctor("Doctor", "7", new Date(), 777777777, "bio 7", "uni 7", 7, "Cardiologist");
        Doctor d8 = new Doctor("Doctor", "8", new Date(), 888888888, "bio 8", "uni 8", 8, "Family Physician");
        Doctor d9 = new Doctor("Doctor", "9", new Date(), 999999999, "bio 9", "uni 9", 9, "Pediatrician");

        initRecyclerView();

    }

    public void search(View view){
        filteredResults.clear();
        if (Doctor.specialization.containsKey(specFilter)) {
            for (Doctor d : Doctor.specialization.get(specFilter)){
                filteredResults.add(d);
            }
        }
        nbrResults.setText(String.valueOf(filteredResults.size()) + " results");
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.doctorRecycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, filteredResults);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.specializationSpinner)
            specFilter = parent.getItemAtPosition(position).toString().toLowerCase();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}