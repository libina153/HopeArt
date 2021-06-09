package com.example.hopeart.Adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hopeart.Activity.ArtistArtworkDetailActivity;
import com.example.hopeart.Activity.CustArtworkOrderDetailActivity;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.CustOrderModel;
import com.example.hopeart.R;

import java.util.List;

public class CustArtworkOrderAdapter extends RecyclerView.Adapter<CustArtworkOrderAdapter.ItemViewHolder> {
    List<ArtistArtworkOrderModel> custartworklist;
    Context ctx;

    public CustArtworkOrderAdapter(List<ArtistArtworkOrderModel> custartworklist, Context ctx) {
        this.custartworklist=custartworklist;
        this.ctx=ctx;
    }

    public void setArtworklist(List<ArtistArtworkOrderModel> custartworklist) {
        this.custartworklist = custartworklist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustArtworkOrderAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustArtworkOrderAdapter.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_adapter_artworkorder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position)
    {
       ArtistArtworkOrderModel custorder=custartworklist.get(position);

        holder.txtArtPrice.setText(String.valueOf(custorder.getArtworkPrice()));
        holder.txtFrameSize.setText(custorder.getArtworkFrameSize());
        holder.txtArtworkType.setText(custorder.getArtworktype());

        holder.btnartworkdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent artdetail=new Intent(ctx, CustArtworkOrderDetailActivity.class);
                ctx.startActivity(artdetail);

            }
        });
        Glide.with(ctx)
                .load(custorder.getArtworkimg())
                .into(holder.imgArtworkImage);

    }

    @Override
    public int getItemCount() {
        return custartworklist.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
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
