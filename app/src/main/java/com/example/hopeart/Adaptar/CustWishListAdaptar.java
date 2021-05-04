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
import com.bumptech.glide.GlideBuilder;
import com.example.hopeart.Activity.ArtworkHomeDetailsActivity;
import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.CustWishListModel;
import com.example.hopeart.R;

import java.util.List;

public class CustWishListAdaptar extends RecyclerView.Adapter<CustWishListAdaptar.ItemViewHolder> {

    List<CustWishListModel> wishlist;
    Context ctx;

    public CustWishListAdaptar(List<CustWishListModel> wishlist, Context ctx) {
        this.wishlist = wishlist;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public CustWishListAdaptar.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustWishListAdaptar.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_adaptar_wishlist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustWishListAdaptar.ItemViewHolder holder, int position) {
        CustWishListModel wish = wishlist.get(position);

        holder.txtWLArtworktype.setText(String.valueOf(wish.getWishListType()));
        holder.txtWLPrice.setText(String.valueOf(wish.getWishListPrice()));

        Glide.with(ctx)
                .load(wish.getWishListImg())
                .into(holder.imgWLImage);
    }
        @Override
        public int getItemCount () {
            return wishlist.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView txtWLArtworktype, txtWLPrice;
            ImageView imgWLImage;


            public ItemViewHolder(@NonNull View itemView) {
                super(itemView);

                this.txtWLArtworktype = itemView.findViewById(R.id.txtWLArtworkType);
                this.txtWLPrice = itemView.findViewById(R.id.txtWLPrice);
                this.imgWLImage = itemView.findViewById(R.id.imgWLimage);
            }

        }
    }
