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
import com.example.hopeart.Activity.ActivityLogIn;
import com.example.hopeart.Activity.ActivitySplash;
import com.example.hopeart.Activity.ArtistCustomDetailActivity;
import com.example.hopeart.DataModel.ArtistArtworkOrderModel;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;

import java.util.List;

public class ArtistCustomizeOrderAdaptar extends RecyclerView.Adapter<ArtistCustomizeOrderAdaptar.ItemViewHolder>{

    List<ArtistCustomizeOrderModel> customlist;
    Context ctx;

    public ArtistCustomizeOrderAdaptar(List<ArtistCustomizeOrderModel> customlist, Context ctx) {
        this.customlist = customlist;
        this.ctx = ctx;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistCustomizeOrderAdaptar.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.artist_adaptar_customizeorder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ArtistCustomizeOrderModel customorder=customlist.get(position);

        holder.txtCustomOrderDt.setText(customorder.getCustomOrderDate());
        holder.txtCustomType.setText(customorder.getCustomerType());
        holder.txtCustomFrameSize.setText(customorder.getCustomFrameSize());
        holder.btncustomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cudetail=new Intent(ctx, ArtistCustomDetailActivity.class);
                ctx.startActivity(cudetail);

            }
        });

        Glide.with(ctx)
                .load(customorder.getCustomPhoto())
                .into(holder.imgCustomImage);

    }

    @Override
    public int getItemCount() {
        return customlist.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtCustomType,txtCustomFrameSize,txtCustomOrderDt;
        ImageView imgCustomImage;
        Button btncustomDetails;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtCustomType = itemView.findViewById(R.id.txtcustomType);
            this.imgCustomImage = itemView.findViewById(R.id.imgcustomPhoto);
            this.txtCustomFrameSize = itemView.findViewById(R.id.txtcustomFrameSize);
            this.txtCustomOrderDt = itemView.findViewById(R.id.txtcustomOrderDate);

            this.btncustomDetails=itemView.findViewById(R.id.btncustomDetails);
        }

    }

    }

