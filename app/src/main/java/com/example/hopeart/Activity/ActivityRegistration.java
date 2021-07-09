package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.example.hopeart.Utility.SharedPreferenceManger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityRegistration extends AppCompatActivity{

    EditText edtRegEmail,edtRegConfirmPass;
    FloatingActionButton btnReg;
    RadioGroup user;
    TextView txtLogin;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();
        edtRegEmail = findViewById(R.id.edtRegEmail);
        edtRegConfirmPass = findViewById(R.id.edtRegConfirmPass);

        txtLogin=findViewById(R.id.txtLogin);

        btnReg= findViewById(R.id.btnReg);
        user=(RadioGroup)findViewById(R.id.rgUser);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(edtRegEmail.getText().toString(),edtRegConfirmPass.getText().toString());
            }
        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent=new Intent(ActivityRegistration.this,ActivityLogIn.class);
                startActivity(loginIntent);
                finish();
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
                             SharedPreferenceManger.setIsRegistered(ActivityRegistration.this,true);
                             SharedPreferenceManger.setUserType(ActivityRegistration.this,getCheckUserType());

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
        private String getCheckUserType(){
            int checkId=user.getCheckedRadioButtonId();
            if (checkId==R.id.radiobtn_artist){
                return "A";
            }else {
                return "C";
            }
        }
}
