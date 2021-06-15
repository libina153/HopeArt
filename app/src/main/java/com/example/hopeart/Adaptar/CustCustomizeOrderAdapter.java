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
import com.example.hopeart.Activity.CustCustomizeOrderDetailActivity;
import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;

import java.util.List;

public class CustCustomizeOrderAdapter extends RecyclerView.Adapter<CustCustomizeOrderAdapter.ItemViewHolder>{

    List<ArtistCustomizeOrderModel> cucustomlist;
    Context ctx;

    public CustCustomizeOrderAdapter(List<ArtistCustomizeOrderModel> cucustomlist, Context ctx) {
        this.cucustomlist = cucustomlist;
        this.ctx = ctx;
    }

    public void setCustomlist(List<ArtistCustomizeOrderModel> cucustomlist) {
        this.cucustomlist = cucustomlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustCustomizeOrderAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustCustomizeOrderAdapter.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_adapter_customizeorder,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustCustomizeOrderAdapter.ItemViewHolder holder, int position) {
        ArtistCustomizeOrderModel cucustomorder=cucustomlist.get(position);

        holder.txtCustomOrderDt.setText(cucustomorder.getCustomOrderDate());
        holder.txtCustomType.setText(cucustomorder.getCustomerType());
        holder.txtCustomFrameSize.setText(cucustomorder.getCustomFrameSize());
        holder.btncustomDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cudetail=new Intent(ctx, CustCustomizeOrderDetailActivity.class);
                cudetail.putExtra("custmizeOrderModel",cucustomorder);
                ctx.startActivity(cudetail);

            }
        });

        Glide.with(ctx)
                .load(cucustomorder.getCustomPhoto())
                .into(holder.imgCustomImage);

    }

    @Override
    public int getItemCount() {
        return cucustomlist.size();
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
