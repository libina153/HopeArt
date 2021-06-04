package com.example.hopeart.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ArtistCustomDetailActivity extends AppCompatActivity {

    TextView txtCustomDetailsFrameSize, txtCustomDetailsPaperType, txtCustomDetailsArtworkType,txtCustomDetailsTakeAction;
    ImageView imgCustomDetails;
    RadioGroup  radiobtnCustomDetails;
    RadioButton radioBtnApproved,radioBtnCancel;
    Button btnCustomDetailsProceed;

    FirebaseFirestore fiDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_activity_customdetails);

        ArtistCustomizeOrderModel artistCustomizeOrderModel
                =(ArtistCustomizeOrderModel) getIntent().getSerializableExtra("model");

        txtCustomDetailsArtworkType=findViewById(R.id.txtCustomDetailsArtworkType);
        txtCustomDetailsPaperType=findViewById(R.id.txtCustomDetailsPaperType);
        txtCustomDetailsFrameSize=findViewById(R.id.txtCustomDetailsFrameSize);
        txtCustomDetailsTakeAction=findViewById(R.id.txtCustomDetailsTakeAction);

        imgCustomDetails=findViewById(R.id.imgCustomDetails);

        radiobtnCustomDetails=findViewById(R.id.radiobtnCustomDetails);

        radioBtnApproved=findViewById(R.id.radiobtn_approved);
        radioBtnCancel=findViewById(R.id.radiobtn_cancel);

        btnCustomDetailsProceed=findViewById(R.id.btnProceed);

        fiDB=FirebaseFirestore.getInstance();

        txtCustomDetailsPaperType.setText(artistCustomizeOrderModel.getCustomPaperType());
        txtCustomDetailsArtworkType.setText(artistCustomizeOrderModel.getCustomerType());
        txtCustomDetailsFrameSize.setText(artistCustomizeOrderModel.getCustomFrameSize());


        Glide.with(this)
                .load(artistCustomizeOrderModel.getCustomPhoto())
                .into(imgCustomDetails);

        btnCustomDetailsProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Map<String,Object> data=new HashMap<>();
                data.put("customOrderStatus",getCheckUserType().toString());
                fiDB.collection("CustomOrderData")
                        .document(artistCustomizeOrderModel.getCustomid())
                        .update(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ArtistCustomDetailActivity.this,"Data Update",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ArtistCustomDetailActivity.this,"Data Update Fail",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
    private String getCheckUserType(){
        int checkId=radiobtnCustomDetails.getCheckedRadioButtonId();
        if (checkId==R.id.radiobtn_approved){
            return "Approved";
        }else {
            return "Cancel";
        }
    }
}
