package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.example.hopeart.Utility.SharedPreferenceManger;


public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!SharedPreferenceManger.getIsRegistered(ActivitySplash.this)){
                    Intent regI=new Intent(ActivitySplash.this, ActivityRegistration.class);
                    startActivity(regI);
                    finish();
                }else{
                    if (!SharedPreferenceManger.getIsLogin(ActivitySplash.this)){
                        Intent log=new Intent(ActivitySplash.this, ActivityLogIn.class);
                        startActivity(log);
                        finish();
                    }else {
                        if (SharedPreferenceManger.getUserType(ActivitySplash.this).equals("A")){
                            Intent artHome=new Intent(ActivitySplash.this, ArtistHomeBottomNav.class);
                            startActivity(artHome);
                            finish();
                        }else {
                            Intent custHome=new Intent(ActivitySplash.this, CustHomeDrawer.class);
                            startActivity(custHome);
                            finish();
                        }
                    }
                }
            }
        },3000);
    }
}
