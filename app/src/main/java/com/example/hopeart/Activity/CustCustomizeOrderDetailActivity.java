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
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.DataModel.ArtistPaymentModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.UtilityMethods;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class CustCustomizeOrderDetailActivity extends AppCompatActivity {

    TextView txtCustArtWorkType,txtCustFrameSize,txtCustArtworkPrice,txtCustPaperType,txtCustOrderDate;
    Button btnPay,btnCancel;
    ImageView imgCustomOrderDetail;

    FirebaseFirestore firebaseDB;

    String artistId;
    String customType;
    String customPhoto;
    String customFrameSize;
    String customPaperType;
    float customPrice;
    String customOrderDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_activity_customorder_detail);

        firebaseDB= FirebaseFirestore.getInstance();

        txtCustArtWorkType=findViewById(R.id.txtCustType);
        txtCustFrameSize=findViewById(R.id.txtCustFrameSize);
        txtCustPaperType=findViewById(R.id.txtCustPaperType);
        txtCustOrderDate=findViewById(R.id.txtCustOrderDate);
        txtCustArtworkPrice=findViewById(R.id.txtCustPrice);

        btnPay=findViewById(R.id.btnPay);
        btnCancel=findViewById(R.id.btnCancel);

        imgCustomOrderDetail=findViewById(R.id.imgCustomOrderDetail);

        ArtistCustomizeOrderModel artistCustomizeOrderModel
                = (ArtistCustomizeOrderModel) getIntent()
                .getSerializableExtra("custmizeOrderModel");

        txtCustArtWorkType.setText(artistCustomizeOrderModel.getCustomerType());
        txtCustFrameSize.setText(artistCustomizeOrderModel.getCustomFrameSize());
        txtCustPaperType.setText(artistCustomizeOrderModel.getCustomPaperType());
        txtCustOrderDate.setText(artistCustomizeOrderModel.getCustomOrderDate());

        txtCustArtworkPrice.setText(getResources().getString(R.string.Rs)+" "+String.valueOf(artistCustomizeOrderModel.getCustomPrice()));

        Glide.with(this)
                .load(artistCustomizeOrderModel.getCustomPhoto())
                .into(imgCustomOrderDetail);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customType=artistCustomizeOrderModel.getCustomerType();
                customFrameSize=artistCustomizeOrderModel.getCustomFrameSize();
                customPaperType=artistCustomizeOrderModel.getCustomPaperType();
                customOrderDate=artistCustomizeOrderModel.getCustomOrderDate();
                customPrice=artistCustomizeOrderModel.getCustomPrice();
                customPhoto=artistCustomizeOrderModel.getCustomPhoto();

                ArtistPaymentModel st=new ArtistPaymentModel("paymentid",artistId,"custId","cash",customPrice,"orderid","Pending", UtilityMethods.getDateAndTime(),customPhoto);

                Map<String,Object> data=st.toMap();

                firebaseDB.collection("PaymentData")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(CustCustomizeOrderDetailActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                String insertedId=documentReference.getId();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CustCustomizeOrderDetailActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
