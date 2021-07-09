package com.example.hopeart.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityForgetPass  extends AppCompatActivity {

    EditText edtEmailId;
    Button btnRestPass,btnBack;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        edtEmailId=findViewById(R.id.edtEmailId);
        btnRestPass=findViewById(R.id.btnRestPass);
        btnBack=findViewById(R.id.btnBack);

        firebaseAuth=FirebaseAuth.getInstance();

        btnRestPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edtEmailId.getText().toString();
                if (!TextUtils.isEmpty(email)){
                    firebaseAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ActivityForgetPass.this, "Password Reset Link Sent", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(ActivityForgetPass.this, "Password Reset Link Not Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(ActivityForgetPass.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
