package com.example.hopeart.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.UserProfileModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

public class CustPlaceOrderActivity extends AppCompatActivity {
    TextView txtArtistName,txtArtistAdress,txtArtistMobno,txtFrameSize,txtPaperType,txtRs;
    ImageView image;
    Button btnplaceOrder;

    String artistId;
    String artworktype;
    String artworkimg;
    String artworkFrameSize;
    float artworkPrice;
    String artworkPaperType;


    StorageReference rootReference;
    FirebaseFirestore firebaseDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_placeorder);

        firebaseDB=FirebaseFirestore.getInstance();

        txtArtistName=findViewById(R.id.txtArtistName);
        txtArtistAdress=findViewById(R.id.txtArtistAdress);
        txtArtistMobno=findViewById(R.id.txtArtistMobno);

        btnplaceOrder=findViewById(R.id.btnplaceOrder);
        txtFrameSize=findViewById(R.id.txtFrameSize);
        txtPaperType=findViewById(R.id.txtPaperType);
        txtRs=findViewById(R.id.txtRs);
        image=findViewById(R.id.image);

        ArtistArtWorkModel artModel=(ArtistArtWorkModel)
                getIntent().getSerializableExtra("artmodel");

        txtPaperType.setText(artModel.getStrArtWorkPaperType());
        txtFrameSize.setText(artModel.getStrArtWorkFrameSize());
        txtRs.setText(String.valueOf(artModel.getArtWorkPrice()));

        artworkimg=artModel.getStrArtWorkImage();

        Glide.with(this)
                .load(artworkimg)
                .into(image);

        btnplaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                artworkFrameSize=artModel.getStrArtWorkFrameSize();
                artworkPaperType=artModel.getStrArtWorkPaperType();
                artworkPrice=artModel.getArtWorkPrice();
                artworkimg=artModel.getStrArtWorkImage();


                ArtistArtworkOrderModel st=new ArtistArtworkOrderModel(artistId,artworktype,artworkimg,artworkFrameSize,artworkPrice,artworkPaperType,"Pending");

                Map<String,Object> data=st.toMap();

                firebaseDB.collection("ArtworkOrderData")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(CustPlaceOrderActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                String insertedId=documentReference.getId();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CustPlaceOrderActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
