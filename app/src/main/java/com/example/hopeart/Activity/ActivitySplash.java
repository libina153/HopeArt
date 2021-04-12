package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;


public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent regI=new Intent(ActivitySplash.this, ActivityRegistration.class);
                startActivity(regI);
            }
        },3000);
    }
}
