package com.example.hopeart.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class ActivityLogIn extends AppCompatActivity {

    EditText edtLoginEmail,edtLoginPass;
    FloatingActionButton btnLogin;
    FirebaseAuth mAuth;
    TextView txtloginReg,txtloginForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= FirebaseAuth.getInstance();

        edtLoginEmail=findViewById(R.id.edtloginEmail);
        edtLoginPass=findViewById(R.id.edtloginPass);
        txtloginReg=findViewById(R.id.txtloginReg);
        txtloginForgotPass=findViewById(R.id.txtloginForgotPass);

        txtloginForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent forgPassIntent=new Intent(ActivityLogIn.this,ActivityForgetPass.class);
                startActivity(forgPassIntent);
            }
        });
        txtloginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regwn=new Intent(ActivityLogIn.this, ActivityRegistration.class);
                startActivity(regwn);
                finish();
            }
        });

        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(edtLoginEmail.getText().toString(),edtLoginPass.getText().toString());
            }
            private void loginUser(String email,String pass) {
                mAuth.signInWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    SharedPreferenceManger.setIsLogin(ActivityLogIn.this,true);

                                    if (SharedPreferenceManger.getUserType(ActivityLogIn.this).equals("A")){
                                        Intent homeIntent=new Intent(ActivityLogIn.this, ArtistAddProfile.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }else{
                                        Intent custHome=new Intent(ActivityLogIn.this, CustAddProfile.class);
                                        startActivity(custHome);
                                        finish();
                                    }
                                    Toast.makeText(ActivityLogIn.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                }else{
                                    Toast.makeText(ActivityLogIn.this, "Login Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActivityLogIn.this, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
