package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
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
    RadioButton rbtnArtist,rbtnCustomer;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        rbtnArtist = findViewById(R.id.radiobtn_artist);

        rbtnArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextArtistActivity();
            }

            private void onNextArtistActivity() {
                Intent i = new Intent(ActivityRegistration.this, ArtistAddProfile.class);
                startActivity(i);
            }
        });
        rbtnCustomer = findViewById(R.id.radiobtn_customer);

        rbtnCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNextCustomerActivity();
            }

            private void onNextCustomerActivity() {
                Intent i = new Intent(ActivityRegistration.this, CustAddProfile.class);
                startActivity(i);
            }
        });

        mAuth=FirebaseAuth.getInstance();

        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegConfirmPass = findViewById(R.id.edtRegConfirmPass);

        btnReg= findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(edtRegEmail.getText().toString(),edtRegConfirmPass.getText().toString());
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
