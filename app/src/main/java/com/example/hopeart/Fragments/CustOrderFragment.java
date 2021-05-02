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

import com.example.hopeart.Adaptar.CustCartAdapter;
import com.example.hopeart.Adaptar.CustOrderAdapter;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.DataModel.CustOrderModel;
import com.example.hopeart.R;

import java.util.ArrayList;
import java.util.List;

public class CustOrderFragment extends Fragment {

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
        return inflater.inflate(R.layout.cust_fragment_order, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvcustorder=view.findViewById(R.id.custOrderRecycleviewer);

        CustOrderModel c=new CustOrderModel("1","12/02/2020","Order","img","11","5*5","Painting",200.0f);
        CustOrderModel c1=new CustOrderModel("2","13/02/2020","pending","image","12","5*5","painting",300.00f);

        List<CustOrderModel> orderlist=new ArrayList<>();

        orderlist.add(c);
        orderlist.add(c1);

        CustOrderAdapter orderAdapter=new CustOrderAdapter(orderlist,ctx);
        rvcustorder.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        rvcustorder.setAdapter(orderAdapter);
    }
}
