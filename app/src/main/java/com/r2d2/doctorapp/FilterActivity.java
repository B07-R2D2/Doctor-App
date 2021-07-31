package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner specializationSpinner;
    private Spinner genderSpinner;
    private TextView txtResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        txtResults = findViewById(R.id.resultsCountText);

        specializationSpinner = findViewById(R.id.specializationSpinner);
        String[] exampleSpecializations = {"Dermatologist", "Cardiologist", "Family Physician", "Pediatrician", "Psychiatrist"};
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exampleSpecializations);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specializationSpinner.setAdapter(adapter1);

        genderSpinner = findViewById(R.id.genderSpinner);
        String[] exampleGenders = {"Male", "Female"};
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, exampleGenders);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter2);

    }

    public void search(View view){
        if (txtResults.getText().equals("0 results"))
            txtResults.setText("1 results");
        else
            txtResults.setText("0 results");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}