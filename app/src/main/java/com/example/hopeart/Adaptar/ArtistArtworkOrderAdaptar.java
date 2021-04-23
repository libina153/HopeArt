package com.example.hopeart.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.Activity.ArtistArtworkDetailActivity;
import com.example.hopeart.Activity.ArtistCustomDetailActivity;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.Fragments.ArtistArtworkOrderFragment;
import com.example.hopeart.R;

import java.util.List;

public class ArtistArtworkOrderAdaptar extends RecyclerView.Adapter<ArtistArtworkOrderAdaptar.ItemViewHolder> {

    List<ArtistArtworkOrderModel> artworklist;
    Context ctx;

    public ArtistArtworkOrderAdaptar(List<ArtistArtworkOrderModel> artworklist, Context ctx) {
        this.artworklist = artworklist;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        return new ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.artist_adaptar_artworkorder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ArtistArtworkOrderModel artworkorder=artworklist.get(position);

        holder.txtArtPrice.setText(String.valueOf(artworkorder.getArtworkPrice()));
        holder.txtFrameSize.setText(artworkorder.getArtworkFrameSize());
        holder.txtArtworkType.setText(artworkorder.getArtworktype());

        holder.btnartworkdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artdetail=new Intent(ctx, ArtistArtworkDetailActivity.class);
                ctx.startActivity(artdetail);

            }
        });

    }

    @Override
    public int getItemCount() {
        return artworklist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView txtArtworkType,txtFrameSize,txtArtPrice;
        ImageView imgArtworkImage;
        Button btnartworkdetail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtArtworkType=itemView.findViewById(R.id.txtartworkType);
            this.imgArtworkImage=itemView.findViewById(R.id.imgartworkImage);
            this.txtFrameSize=itemView.findViewById(R.id.txtartworkFrameSize);
            this.txtArtPrice=itemView.findViewById(R.id.txtartworkPrice);
            this.btnartworkdetail=itemView.findViewById(R.id.btnartistdetail);

        }
    }
}