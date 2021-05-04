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

import com.example.hopeart.Adaptar.ArtistCustomizeOrderAdaptar;
import com.example.hopeart.Adaptar.CustHomeAdaptar;
import com.example.hopeart.Adaptar.CustWishListAdaptar;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.DataModel.CustWishListModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.AppDatabase;
import com.example.hopeart.Utility.DatabaseClient;

import java.util.ArrayList;
import java.util.List;

public class CustWishListFragment extends Fragment {

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
        return inflater.inflate(R.layout.cust_fragment_wishlist, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView wList=view.findViewById(R.id.wishListRecycleviewer);

        List<CustWishListModel> wishList=new ArrayList<>();

        AppDatabase appDatabase= DatabaseClient.getInstance(ctx)
                .getAppDatabase();

        wishList=appDatabase.custWishListDao().getWishListModel();

        CustWishListAdaptar wishLadapter=new CustWishListAdaptar(wishList,ctx);
        wList.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        wList.setAdapter(wishLadapter);

    }
}
