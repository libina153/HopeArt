package com.example.hopeart.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.Adaptar.ArtistCustomizeOrderAdaptar;
import com.example.hopeart.Adaptar.ArtistPaymentAdaptar;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.DataModel.ArtistPaymentModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ArtistPaymentFragment extends Fragment {

    Context ctx;
    FirebaseFirestore DB;
    List<ArtistPaymentModel> paymentlist=null;
    ArtistPaymentAdaptar paymentadapter=null;

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
        return inflater.inflate(R.layout.artist_fragment_payment, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        {

            RecyclerView rvpayment = view.findViewById(R.id.paymentRecycleviewer);

            DB.collection("PaymentData")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            paymentlist=new ArrayList<>();
                            if (task.isSuccessful()){
                                for (DocumentSnapshot doc : task.getResult()){
                                    ArtistPaymentModel orderModel=doc.toObject(ArtistPaymentModel.class);
                                    orderModel.setPaymentId(doc.getId());
                                    paymentlist.add(orderModel);
                                }
                                paymentadapter=new ArtistPaymentAdaptar(paymentlist,ctx);
                                rvpayment.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
                                rvpayment.setAdapter(paymentadapter);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });

        }
    }
}