package com.example.hopeart.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.Adaptar.ArtistHomeAdaptar;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistHomeFragment extends Fragment {
    Context ctx;


    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        ctx=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.artist_fragment_home,container,attachToRoot=false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvartworkHomeOrder=view.findViewById(R.id.artworkHomeRecycleviewer);

        ArtistArtWorkModel a=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);
        ArtistArtWorkModel a1=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);
        ArtistArtWorkModel a2=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);
        ArtistArtWorkModel a3=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);
        ArtistArtWorkModel a4=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);
        ArtistArtWorkModel a5=new ArtistArtWorkModel("Painting","8*8","Cartridge Paper",800);



        List<ArtistArtWorkModel> homelist=new ArrayList<>();

        homelist.add(a);
        homelist.add(a1);
        homelist.add(a2);
        homelist.add(a3);
        homelist.add(a4);
        homelist.add(a5);


        ArtistHomeAdaptar homeAdaptar=new ArtistHomeAdaptar(homelist,ctx);
        rvartworkHomeOrder.setLayoutManager(new GridLayoutManager(ctx,2,GridLayoutManager.VERTICAL,true));
        rvartworkHomeOrder.setAdapter(homeAdaptar);

    }
}
