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
import com.example.hopeart.Adaptar.ArtistCustomizeOrderAdaptar;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistCustomizeOrderFrag extends Fragment {
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
        return inflater.inflate(R.layout.artist_fragment_customizeorder,container,attachToRoot=false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvcustomOrder=view.findViewById(R.id.customRecycleviewer);

        ArtistCustomizeOrderModel a=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");
        ArtistCustomizeOrderModel a1=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","20/04/2021","Sketch");
        ArtistCustomizeOrderModel a2=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");
        ArtistCustomizeOrderModel a3=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");
        ArtistCustomizeOrderModel a4=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");
        ArtistCustomizeOrderModel a5=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");
        ArtistCustomizeOrderModel a6=new ArtistCustomizeOrderModel("1","1","1","photo","8*8","Cartridge Paper","Pending","23/04/2021","Painting");



        List<ArtistCustomizeOrderModel> customlist=new ArrayList<>();

        customlist.add(a);
        customlist.add(a1);
        customlist.add(a2);
        customlist.add(a3);
        customlist.add(a4);
        customlist.add(a5);
        customlist.add(a6);

        ArtistCustomizeOrderAdaptar artworkadapter=new ArtistCustomizeOrderAdaptar(customlist,ctx);
        rvcustomOrder.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        rvcustomOrder.setAdapter(artworkadapter);


    }
}