package com.example.hopeart.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hopeart.DataModel.CustOrderModel;
import com.example.hopeart.R;

import java.util.List;

public class CustOrderAdapter extends RecyclerView.Adapter<CustOrderAdapter.ItemViewHolder> {
    List<CustOrderModel> orderlist;
    Context ctx;

    public CustOrderAdapter(List<CustOrderModel> orderlist, Context ctx) {
        this.orderlist=orderlist;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public CustOrderAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustOrderAdapter.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_order_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position)
    {
       CustOrderModel custorder=orderlist.get(position);

       holder.txtOrderDate.setText(custorder.getStrOrderDate());
       holder.txtOrderType.setText(custorder.getStrOrderType());
       //holder.txtOrderFrameSize.setText(custorder.getStrOrderFrameSize());
       holder.txtOrderStatus.setText(custorder.getStrOrderStatus());
        holder.txtOrderPrice.setText(String.valueOf(custorder.getAmount()));
    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtOrderId,txtOrderDate,txtOrderStatus,txtOrderCustId,txtOrderFrameSize,txtOrderType,txtOrderPrice;
        ImageView imgOrderArtwork;

        public ItemViewHolder(View itemview)
        {
            super(itemview);

            //this.txtOrderId=itemview.findViewById(R.id.txtOrderId);
            this.txtOrderDate=itemview.findViewById(R.id.txtOrderDate);
            this.txtOrderStatus=itemview.findViewById(R.id.txtOrderStatus);
            //this.txtOrderCustId=itemview.findViewById(R.id.txtOrderCustId);
            //this.txtOrderFrameSize=itemview.findViewById(R.id.txtOrderFrameSize);
            this.txtOrderType=itemview.findViewById(R.id.txtOrderType);
            this.txtOrderPrice=itemview.findViewById(R.id.txtOrderAmount);
            //this.imgOrderArtwork=itemview.findViewById(R.id.imgOrderArtwork);
        }
    }
}
