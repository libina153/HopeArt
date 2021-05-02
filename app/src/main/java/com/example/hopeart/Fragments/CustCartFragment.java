package com.example.hopeart.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.Adaptar.CustCartAdapter;
import com.example.hopeart.Adaptar.CustOrderAdapter;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.R;

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

        CustCartModel c=new CustCartModel("image","4*5",500.0f,1);
        CustCartModel c1=new CustCartModel("image","4*5",300.0f,2);

        List<CustCartModel> cartlist=new ArrayList<>();

        cartlist.add(c);
        cartlist.add(c1);

        CustCartAdapter cartAdapter=new CustCartAdapter(cartlist,ctx);
        rvcustcart.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        rvcustcart.setAdapter(cartAdapter);
    }
}
