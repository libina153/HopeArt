package com.example.hopeart.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hopeart.DataModel.CustCartModel;
import com.example.hopeart.R;

import java.util.List;

public class CustCartAdapter extends RecyclerView.Adapter<CustCartAdapter.ItemViewHolder>
{
    List<CustCartModel> cartlist;
    Context ctx;

    public CustCartAdapter(List<CustCartModel> cartlist,Context ctx){
        this.cartlist=cartlist;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public CustCartAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustCartAdapter.ItemViewHolder(LayoutInflater.from(ctx).inflate(R.layout.cust_cart_adapter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position)
    {
      CustCartModel custcart=cartlist.get(position);

      holder.txtCartPrice.setText(String.valueOf(custcart.getCustCartPrice()));

        Glide.with(ctx)
                .load(custcart.getStrCartImg())
                .into(holder.imgCart);
    }

    public int getItemCount(){return cartlist.size();}

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCartPrice;
        ImageView imgCart;
        Button btnCartRemove;

        public ItemViewHolder(View itemview)
        {
            super(itemview);

            this.txtCartPrice=itemview.findViewById(R.id.txtCartPrice);
            this.imgCart=itemview.findViewById(R.id.cartCancleImage);
            this.btnCartRemove=itemview.findViewById(R.id.btnCartRemove);
        }
    }
}

