package com.r2d2.doctorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        Thread splash = new Thread(){
//            @Override
//            public void run() {
//                try{
//                    sleep(4*1000);
//                    //finish(); //if you put finish() here, the transition looks different, no idea why
//                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                    // this doesn't work :(
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                }
//                catch (InterruptedException e){
//
//                }
//            }
//        };
//        splash.start();

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.splashLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}