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
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Adaptar.ArtistArtworkOrderAdaptar;
import com.example.hopeart.Adaptar.ArtistHomeAdaptar;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ArtistHomeFragment extends Fragment {
    Context ctx;
    FirebaseFirestore DB;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        ctx=context;
        DB=FirebaseFirestore.getInstance();
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

        DB.collection("ArtWorkData")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<ArtistArtWorkModel> artworklist=new ArrayList<>();
                        if (task.isSuccessful()){
                            for (DocumentSnapshot doc : task.getResult()){
                                ArtistArtWorkModel artworkModel=doc.toObject(ArtistArtWorkModel.class);
                                artworkModel.setStrArtWorkId(doc.getId());
                                artworklist.add(artworkModel);
                            }
                            ArtistHomeAdaptar homeAdaptar=new ArtistHomeAdaptar(artworklist,ctx);
                            rvartworkHomeOrder.setLayoutManager(new GridLayoutManager(ctx,2,GridLayoutManager.VERTICAL,true));
                            rvartworkHomeOrder.setAdapter(homeAdaptar);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}

