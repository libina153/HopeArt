package com.example.hopeart.Activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.DataModel.CustWishListModel;
import com.example.hopeart.Fragments.CustWishListFragment;
import com.example.hopeart.R;
import com.example.hopeart.Utility.AppDatabase;
import com.example.hopeart.Utility.DatabaseClient;

public class ArtworkHomeDetailsActivity extends AppCompatActivity {

    Button btnAddwishList,btnPlaceOrder;
    TextView txtHDArtworkType,txtHDFrameSize,txtHDPaperType,txtHDPrice;
    ImageView imgHD;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artwork_activity_homedetails);

        ArtistArtWorkModel artModel=(ArtistArtWorkModel) getIntent().getSerializableExtra("model");

        txtHDArtworkType=findViewById(R.id.txtHDArtworkType);
        txtHDFrameSize=findViewById(R.id.txtHDFrameSize);
        txtHDPaperType=findViewById(R.id.txtHDPaperType);
        txtHDPrice=findViewById(R.id.txtHDPrice);
        imgHD=findViewById(R.id.imgHD);

        Glide.with(this)
                .load(artModel.getStrArtWorkImage())
                .into(imgHD);

        txtHDArtworkType.setText(artModel.getStrArtWorkType());
        txtHDFrameSize.setText(artModel.getStrArtWorkFrameSize());
        txtHDPaperType.setText(artModel.getStrArtWorkPaperType());
        txtHDPrice.setText(String.valueOf(artModel.getArtWorkPrice()));

        btnAddwishList=findViewById(R.id.btnAddWishList);
        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);

        AppDatabase appDatabase= DatabaseClient
                                .getInstance(getApplicationContext())
                                .getAppDatabase();

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i=new Intent(ArtworkHomeDetailsActivity.this,CustPlaceOrderActivity.class);
             i.putExtra("artmodel",artModel);
             startActivity(i);
            }
        });

        btnAddwishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustWishListModel cwm=new CustWishListModel(
                        artModel.getStrArtWorkImage(),
                        artModel.getArtWorkPrice(),
                        artModel.getStrArtWorkType());

                appDatabase.custWishListDao().insertCustWishListModel(cwm);
                Toast.makeText(ArtworkHomeDetailsActivity.this, "Added To Wish List", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
