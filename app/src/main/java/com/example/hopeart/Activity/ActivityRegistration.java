package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistration extends AppCompatActivity{

    EditText edtRegEmail,edtRegConfirmPass;
    FloatingActionButton btnReg;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();
        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegConfirmPass = findViewById(R.id.edtRegConfirmPass);

        btnReg= findViewById(R.id.btnReg);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(edtRegEmail.getText().toString(),edtRegConfirmPass.getText().toString());
                RadioGroup user=(RadioGroup)findViewById(R.id.rgUser);

                switch (user.getCheckedRadioButtonId()) {
                    case R.id.radiobtn_artist:
                        Intent i = new Intent(ActivityRegistration.this, ArtistAddProfile.class);
                        startActivity(i);
                        break;

                    case R.id.radiobtn_customer:
                        Intent in = new Intent(ActivityRegistration.this, CustAddProfile.class);
                        startActivity(in);
                        break;
                }
            }
        });
    }

        private void registerUser(String email,String pass)
        {
            mAuth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful())
                         {
                             Toast.makeText(ActivityRegistration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                             Intent loginIntent=new Intent(ActivityRegistration.this,ActivityLogIn.class);
                             startActivity(loginIntent);
                             finish();
                         }
                         else
                         {
                             Toast.makeText(ActivityRegistration.this, "Registration Error", Toast.LENGTH_SHORT).show();
                         }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ActivityRegistration.this, "Failed to register", Toast.LENGTH_SHORT).show();

                }
            });
        }
}
