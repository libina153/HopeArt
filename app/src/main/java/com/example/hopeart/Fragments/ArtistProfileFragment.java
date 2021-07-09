package com.example.hopeart.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.hopeart.R;
import com.example.hopeart.Utility.SharedPreferenceManger;

public class ArtistProfileFragment extends Fragment {


    private Context ctx;
    ImageView  imgProfilePic;
    TextView txtName,txtMobno,txtEmail,txtAadhaarno,txtAddress;
    Button btnEdit;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.artist_fragment_profile, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //find
        txtName=view.findViewById(R.id.txtName);
        txtEmail=view.findViewById(R.id.txtEmail);
        txtMobno=view.findViewById(R.id.txtMobno);
        txtAadhaarno=view.findViewById(R.id.txtAadhaarno);
        txtAddress=view.findViewById(R.id.txtAddress);
        imgProfilePic=view.findViewById(R.id.imgProfilePic);
        btnEdit=view.findViewById(R.id.btnEdit);

        String name= SharedPreferenceManger.getUserName(ctx);
        String email= SharedPreferenceManger.getEmail(ctx);
        String mobno= SharedPreferenceManger.getMobileNo(ctx);
        String aadharno= SharedPreferenceManger.getAadharNo(ctx);
        String address= SharedPreferenceManger.getAddress(ctx);
        String profilepic= SharedPreferenceManger.getProfilePic(ctx);

        //set
        txtName.setText(name);
        txtEmail.setText(email);
        txtMobno.setText(mobno);
        txtAadhaarno.setText(aadharno);
        txtAddress.setText(address);

        Glide.with(ctx)
                .load(profilepic)
                .into(imgProfilePic);
    }
}