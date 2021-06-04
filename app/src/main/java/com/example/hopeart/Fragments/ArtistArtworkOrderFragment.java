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
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistArtworkOrderFragment extends Fragment{
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
        return inflater.inflate(R.layout.artist_fragment_artworkorder,container,attachToRoot=false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        RecyclerView rvartworkOrder=view.findViewById(R.id.artworkRecycleviewer);



        List<ArtistArtworkOrderModel> artworklist=new ArrayList<>();


        ArtistArtworkOrderAdaptar artworkadapter=new ArtistArtworkOrderAdaptar(artworklist,ctx);
        rvartworkOrder.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL,false));
        rvartworkOrder.setAdapter(artworkadapter);

    }
}

