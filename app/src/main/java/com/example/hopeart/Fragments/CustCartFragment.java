package com.example.hopeart.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Activity.ActivityLogIn;
import com.example.hopeart.Activity.ActivityRegistration;
import com.example.hopeart.Activity.CustPlaceOrderActivity;
import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.Adaptar.CustCartAdapter;
import com.example.hopeart.Adaptar.CustOrderAdapter;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.AppDatabase;
import com.example.hopeart.Utility.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class CustCartFragment extends Fragment
{
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
        return inflater.inflate(R.layout.cust_fragment_cart, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvcustcart=view.findViewById(R.id.custCartRecycleviewer);

        List<CustCartModel> cartlist=new ArrayList<>();

        AppDatabase appDatabase= DatabaseClient
                            .getInstance(ctx)
                            .getAppDatabase();

        cartlist=appDatabase.custCartDao().getCustCartModel();

        CustCartAdapter cartAdapter=new CustCartAdapter(cartlist,ctx);
        rvcustcart.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        rvcustcart.setAdapter(cartAdapter);

        Intent loginIntent=new Intent(ctx, CustPlaceOrderActivity.class);
        startActivity(loginIntent);
    }
}
