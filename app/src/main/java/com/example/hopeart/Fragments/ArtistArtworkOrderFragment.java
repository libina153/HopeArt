package com.example.hopeart.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArtistArtworkOrderFragment extends Fragment{
    Context ctx;
    FirebaseFirestore DB;
    List<ArtistArtworkOrderModel> artworklist=null;
    ArtistArtworkOrderAdaptar artworkadapter=null;

    TextView txtArtworkSelectOrderStatus;

    String strArtworkSelectOrderStatus;

    String[] ArtworkSelectOrderStatus = {"Approved", "Pending","Cancel"};


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ctx=context;
        DB=FirebaseFirestore.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.artist_fragment_artworkorder,container,attachToRoot=false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvartworkOrder=view.findViewById(R.id.artworkRecycleviewer);

        DB.collection("ArtworkOrderData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        artworklist=new ArrayList<>();
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                ArtistArtworkOrderModel artworkModel=doc.toObject(ArtistArtworkOrderModel.class);
                                artworkModel.setArtistId(doc.getId());
                                artworklist.add(artworkModel);
                            }
                            List<ArtistArtworkOrderModel> artworklist=new ArrayList<>();

                            artworkadapter=new ArtistArtworkOrderAdaptar(artworklist,ctx);
                            rvartworkOrder.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
                            rvartworkOrder.setAdapter(artworkadapter);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        txtArtworkSelectOrderStatus=view.findViewById(R.id.txtArtworkSelectOrderStatus);

        txtArtworkSelectOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select Artwork Order Status")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(ArtworkSelectOrderStatus, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtArtworkSelectOrderStatus.setText(ArtworkSelectOrderStatus[position]);
                                strArtworkSelectOrderStatus = (ArtworkSelectOrderStatus[position]);

                                artworklist.clear();

                                DB.collection("ArtworkOrderData")
                                        .whereEqualTo("artworkOrderStatus", strArtworkSelectOrderStatus)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                artworklist = new ArrayList<>();
                                                if (task.isSuccessful()) {
                                                    for (DocumentSnapshot doc : task.getResult()) {
                                                        ArtistArtworkOrderModel artworkModel = doc.toObject(ArtistArtworkOrderModel.class);
                                                        artworkModel.setArtistId(doc.getId());
                                                        artworklist.add(artworkModel);
                                                    }
                                                    artworkadapter.setArtworklist(artworklist);
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });
    }
}




