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
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ArtistArtworkDetailActivity extends AppCompatActivity {

    TextView txtArtworkDetailsFrameSize, txtArtworkDetailsPaperType, txtArtworkDetailsArtworkType,txtArtworkDetailsTakeAction;
    ImageView imgArtworkDetails;
    RadioGroup radiobtnArtworkDetails;
    RadioButton radioBtnArtworkApproved,radioBtnArtworkCancel;
    Button btnArtworkDetailsProceed;

    FirebaseFirestore fiDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_activity_artworkdetails);

        ArtistArtworkOrderModel artistArtworkOrderModel
                = (ArtistArtworkOrderModel) getIntent()
                .getSerializableExtra("artworkModel");

        txtArtworkDetailsArtworkType = findViewById(R.id.txtArtworkDetailsArtworkType);
        txtArtworkDetailsPaperType = findViewById(R.id.txtArtworkDetailsPaperType);
        txtArtworkDetailsFrameSize = findViewById(R.id.txtArtworkDetailsFrameSize);
        txtArtworkDetailsTakeAction = findViewById(R.id.txtArtworkDetailsTakeAction);

        imgArtworkDetails = findViewById(R.id.imgArtworkDetails);

        radiobtnArtworkDetails = findViewById(R.id.radiobtnArtworkDetails);

        radioBtnArtworkApproved = findViewById(R.id.radiobtn_artworkApproved);
        radioBtnArtworkCancel = findViewById(R.id.radiobtn_artworkCancel);

        btnArtworkDetailsProceed = findViewById(R.id.btnArtworkProceed);

        fiDB = FirebaseFirestore.getInstance();

        txtArtworkDetailsPaperType.setText(artistArtworkOrderModel.getArtworkPaperType());
        txtArtworkDetailsArtworkType.setText(artistArtworkOrderModel.getArtworktype());
        txtArtworkDetailsFrameSize.setText(artistArtworkOrderModel.getArtworkFrameSize());

        Glide.with(this)
                .load(artistArtworkOrderModel.getArtworkimg())
                .into(imgArtworkDetails);

        btnArtworkDetailsProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> data = new HashMap<>();
                data.put("artworkOrderStatus", getCheckUserType().toString());
                fiDB.collection("ArtworkOrderData")
                        .document(artistArtworkOrderModel.getArtistId())
                        .update(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ArtistArtworkDetailActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ArtistArtworkDetailActivity.this, "Data Update Fail", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

            private String getCheckUserType() {
                int checkId = radiobtnArtworkDetails.getCheckedRadioButtonId();
                if (checkId == R.id.radiobtn_artworkApproved) {
                    return "Approved";
                } else {
                    return "Cancel";
                }
            }
        }