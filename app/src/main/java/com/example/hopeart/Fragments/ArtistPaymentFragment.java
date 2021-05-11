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

import com.example.hopeart.Activity.ActivityLogIn;
import com.example.hopeart.Activity.ActivityRegistration;
import com.example.hopeart.Activity.CustHomeDrawer;
import com.example.hopeart.Adaptar.ArtistHomeAdaptar;
import com.example.hopeart.Adaptar.ArtistPaymentAdaptar;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistPaymentModel;
import com.example.hopeart.R;

import java.util.ArrayList;
import java.util.List;


public class ArtistPaymentFragment extends Fragment {

    Context ctx;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ctx=context;
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

            ArtistPaymentModel a = new ArtistPaymentModel("1","1","1","Paymode","200","1","Status","02-03-2021");
            ArtistPaymentModel a1 = new ArtistPaymentModel("2","2","2","Paymode","200","1","Status","02-03-2021");


            List<ArtistPaymentModel> paymentlist = new ArrayList<>();

            paymentlist.add(a);
            paymentlist.add(a1);



            ArtistPaymentAdaptar paymentAdaptar = new ArtistPaymentAdaptar(paymentlist, ctx);
            rvpayment.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
            rvpayment.setAdapter(paymentAdaptar);



        }

    }
}