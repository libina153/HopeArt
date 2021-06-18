package com.example.hopeart.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistCustomizeOrderAdaptar;
import com.example.hopeart.Adaptar.CustCustomizeOrderAdapter;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustCustomizeOrderFrag extends Fragment {
    Context ctx;
    FirebaseFirestore DB;
    TextView txtSelectOrderStatus;
    List<ArtistCustomizeOrderModel> cuorderlist=null;
    CustCustomizeOrderAdapter cuartworkadapter=null;

    String strSelectOrderStatus;

    String[] SelectOrderStatus = {"Approved", "Pending","Cancel"};

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
        return inflater.inflate(R.layout.cust_fragment_customizeorder,container,attachToRoot=false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvcustomOrder=view.findViewById(R.id.customRecycleviewer);

        txtSelectOrderStatus=view.findViewById(R.id.txtSelectOrderStatus);

        txtSelectOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select Order Status")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(SelectOrderStatus, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtSelectOrderStatus.setText(SelectOrderStatus[position]);
                                strSelectOrderStatus= (SelectOrderStatus[position]);

                                cuorderlist.clear();
                                DB.collection("CustomOrderData")
                                        .whereEqualTo("customOrderStatus",strSelectOrderStatus)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                cuorderlist=new ArrayList<>();
                                                if (task.isSuccessful()){
                                                    for (DocumentSnapshot doc : task.getResult()){
                                                        ArtistCustomizeOrderModel orderModel=doc.toObject(ArtistCustomizeOrderModel.class);
                                                        orderModel.setCustomid(doc.getId());
                                                        cuorderlist.add(orderModel);
                                                    }
                                                    cuartworkadapter.setCustomlist(cuorderlist);
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

        DB.collection("CustomOrderData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        cuorderlist=new ArrayList<>();
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                ArtistCustomizeOrderModel orderModel=doc.toObject(ArtistCustomizeOrderModel.class);
                                orderModel.setCustomid(doc.getId());
                                cuorderlist.add(orderModel);
                            }
                            cuartworkadapter=new CustCustomizeOrderAdapter(cuorderlist,ctx);
                            rvcustomOrder.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
                            rvcustomOrder.setAdapter(cuartworkadapter);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}