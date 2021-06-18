package com.example.hopeart.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hopeart.Activity.ArtistArtworkDetailActivity;
import com.example.hopeart.Activity.ArtistCustomDetailActivity;
import com.example.hopeart.Activity.ArtworkHomeDetailsActivity;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;

import java.util.List;

public class ArtistHomeAdaptar extends RecyclerView.Adapter<ArtistHomeAdaptar.ItemViewHolder> {

    List<ArtistArtWorkModel> homelist;
    Context ctx;

    public ArtistHomeAdaptar(List<ArtistArtWorkModel> homelist, Context ctx) {
        this.homelist = homelist;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ArtistHomeAdaptar.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistHomeAdaptar.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.artist_adaptar_home,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistHomeAdaptar.ItemViewHolder holder, int position) {
        ArtistArtWorkModel home=homelist.get(position);

        holder.txtHomePrice.setText(String.valueOf(home.getArtWorkPrice()));

        Glide.with(ctx)
                .load(home.getStrArtWorkImage())
                .into(holder.imgHomeImage);

         holder.imgHomeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }

         });
    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtHomePrice;
        ImageView imgHomeImage,imgHomeMenu;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtHomePrice = itemView.findViewById(R.id.txtHomePrice);
            this.imgHomeImage = itemView.findViewById(R.id.imgHomeImage);
            this.imgHomeMenu=itemView.findViewById(R.id.imgHomeMenu);


        }

    }


}




