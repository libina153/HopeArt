package com.example.hopeart.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hopeart.Activity.ArtworkHomeDetailsActivity;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.R;

import java.util.List;


public class CustHomeAdaptar extends RecyclerView.Adapter<CustHomeAdaptar.ItemViewHolder> {

    List<ArtistArtWorkModel> custhomelist;
    Context ctx;

    public CustHomeAdaptar(List<ArtistArtWorkModel> custhomelist, Context ctx) {
        this.custhomelist = custhomelist;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public CustHomeAdaptar.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustHomeAdaptar.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_adaptar_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustHomeAdaptar.ItemViewHolder holder, int position) {
        ArtistArtWorkModel home=custhomelist.get(position);

        holder.txtcustHomePrice.setText(String.valueOf(home.getArtWorkPrice()));
        Glide.with(ctx)
                .load(home.getStrArtWorkImage())
                .into(holder.imgcustHomeImage);


        holder.imgcustHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent=new Intent(ctx, ArtworkHomeDetailsActivity.class);
                detailIntent.putExtra("model",home);
                ctx.startActivity(detailIntent);

            }

        });

    }

    @Override
    public int getItemCount() {
        return custhomelist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtcustHomePrice;
        ImageView imgcustHomeImage,imgcustHomeMenu;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtcustHomePrice = itemView.findViewById(R.id.txtcustHomePrice);
            this.imgcustHomeImage = itemView.findViewById(R.id.imgcustHomeImage);
            this.imgcustHomeMenu=itemView.findViewById(R.id.imgcustHomeMenu);


        }

    }


}
