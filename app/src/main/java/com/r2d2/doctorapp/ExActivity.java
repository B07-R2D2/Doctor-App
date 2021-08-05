package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        /*
         * This is currently a empty activity that appears when selecting
         * doctor from the filtering/"find a specialist" page.
         * see line 47 in RecyclerViewAdapter.
         */

        // Some stuff I tried but didn't work
        //Intent intent = getIntent();
        //Doctor doc = (Doctor) intent.getParcelableExtra(RecyclerViewAdapter.EXTRA_MESSAGE);

        //TextView textView = findViewById(R.id.textView);
        //textView.setText(doc.getFirstName() + " " + doc.getLastName());
    }

    public void goBack(View view){
        finish();
    }
}