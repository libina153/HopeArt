package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustAddProfile extends AppCompatActivity {
    FloatingActionButton btn_moveto_custhomee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_addcust_profile_activity);

        btn_moveto_custhomee=findViewById(R.id.btn_moveto_custhome);

        btn_moveto_custhomee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextCustomerActivity();
            }

            private void onNextCustomerActivity() {
                Intent i=new Intent(CustAddProfile.this, CustHomeDrawer.class);
                startActivity(i);
            }
        });





    }
}
