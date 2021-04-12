package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityRegistration extends AppCompatActivity{
    FloatingActionButton btn_moveto_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_activity_registration);
        btn_moveto_login=findViewById(R.id.move_to_login_button);

        btn_moveto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextActivity();
            }

            private void onNextActivity() {
                Intent i=new Intent(ActivityRegistration.this, ActivityLogIn.class);
                startActivity(i);
            }
        });


    }


}
