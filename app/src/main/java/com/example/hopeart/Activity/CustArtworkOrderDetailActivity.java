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
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistPaymentModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.UtilityMethods;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CustArtworkOrderDetailActivity extends AppCompatActivity {

    TextView txtCustArtWorkType,txtCustFrameSize,txtCustArtworkPrice,txtCustPaperType,txtCustOrderDate;
    Button btnPay,btnCancel;
    ImageView imgCustomOrderDetail;

    FirebaseFirestore firebaseDB;

    String artistId;
    String artworktype;
    String artworkimg;
    String artworkFrameSize;
    float artworkPrice;
    String artworkOrderStatus;
    String artworkPaperType;
    String artworkOrderDate;
    String orderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_artworkorder_detail);

        firebaseDB= FirebaseFirestore.getInstance();

        txtCustArtWorkType=findViewById(R.id.txtArtWorkType);
        txtCustFrameSize=findViewById(R.id.txtFrameSize);
        txtCustPaperType=findViewById(R.id.txtPaperType);
        txtCustOrderDate=findViewById(R.id.txtOrderDate);
        txtCustArtworkPrice=findViewById(R.id.txtArtworkPrice);

        btnPay=findViewById(R.id.btnPay);
        btnCancel=findViewById(R.id.btnCancel);

        imgCustomOrderDetail=findViewById(R.id.imgCustomOrderDetail);

        ArtistArtworkOrderModel artistArtworkOrderModel
                = (ArtistArtworkOrderModel) getIntent()
                .getSerializableExtra("custArtworkOrderModel");

        txtCustArtWorkType.setText(artistArtworkOrderModel.getArtworktype());
        txtCustFrameSize.setText(artistArtworkOrderModel.getArtworkFrameSize());
        txtCustPaperType.setText(artistArtworkOrderModel.getArtworkPaperType());
        txtCustOrderDate.setText(artistArtworkOrderModel.getArtworkOrderDate());

        txtCustArtworkPrice.setText(getResources().getString(R.string.Rs)+" "+String.valueOf(artistArtworkOrderModel.getArtworkPrice()));
        Glide.with(this)
                .load(artistArtworkOrderModel.getArtworkimg())
                .into(imgCustomOrderDetail);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                artworktype=artistArtworkOrderModel. getArtworktype();
                artworkFrameSize=artistArtworkOrderModel.getArtworkFrameSize();
                artworkPaperType=artistArtworkOrderModel.getArtworkPaperType();
                artworkPrice=artistArtworkOrderModel.getArtworkPrice();
                artworkimg=artistArtworkOrderModel.getArtworkimg();


                ArtistPaymentModel st=new ArtistPaymentModel("paymentid",artistId,"custId","cash",artworkPrice,"orderid","Pending",UtilityMethods.getDateAndTime(),artworkimg);

                Map<String,Object> data=st.toMap();

                firebaseDB.collection("PaymentData")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(CustArtworkOrderDetailActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                String insertedId=documentReference.getId();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CustArtworkOrderDetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> upData=new HashMap<>();
                upData.put("artworkOrderStatus","Cancel");

                firebaseDB.collection("ArtworkOrderData")
                        .document(artistArtworkOrderModel.getArtistId())
                        .update(upData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(CustArtworkOrderDetailActivity.this, "Order Canceled ", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CustArtworkOrderDetailActivity.this, "Data Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
