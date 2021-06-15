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
import com.example.hopeart.Activity.ArtistCustomDetailActivity;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.DataModel.ArtistPaymentModel;
import com.example.hopeart.R;

import java.util.List;

public class ArtistPaymentAdaptar extends RecyclerView.Adapter<ArtistPaymentAdaptar.ItemViewHolder>{

    List<ArtistPaymentModel> paymentlist;
    Context ctx;

    public ArtistPaymentAdaptar(List<ArtistPaymentModel> paymentlist, Context ctx) {
        this.paymentlist = paymentlist;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ArtistPaymentAdaptar.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistPaymentAdaptar.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.artist_adaptar_payment,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistPaymentAdaptar.ItemViewHolder holder, int position) {
        ArtistPaymentModel paymentorder=paymentlist.get(position);

        holder.txtPaymentMode.setText(paymentorder.getPaymentMode());
        holder.txtPaymentAmt.setText(String.valueOf(paymentorder.getPaymentAmount()));
        holder.txtPaymentDate.setText(paymentorder.getPaymentDate());
        holder.txtPaymentStatus.setText(paymentorder.getPaymentStatus());

        Glide.with(ctx)
                .load(paymentorder.getPaymentimg())
                .into(holder.imgartworkImage);
    }

    @Override
    public int getItemCount() {
        return paymentlist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtPaymentMode,txtPaymentAmt,txtPaymentStatus,txtPaymentDate;
        ImageView imgartworkImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtPaymentMode = itemView.findViewById(R.id.txtPaymentMode);
            this.txtPaymentAmt = itemView.findViewById(R.id.txtPaymentAmt);
            this.txtPaymentDate = itemView.findViewById(R.id.txtPaymentDate);
            this.txtPaymentStatus=itemView.findViewById(R.id.txtPaymentStatus);
            this.imgartworkImage=itemView.findViewById(R.id.imgartworkImage);

        }
    }
}


