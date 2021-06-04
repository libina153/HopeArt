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
import com.example.hopeart.Activity.CustPlaceOrderActivity;
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
        holder.txtCartFrameSize.setText(custcart.getStrFrameSize());

        Glide.with(ctx)
                .load(custcart.getStrCartImg())
                .into(holder.imgCart);

        holder.btnplaceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent placeorder=new Intent(ctx, CustPlaceOrderActivity.class);
                ctx.startActivity(placeorder);

            }
        });


    }

    public int getItemCount(){return cartlist.size();}

    public class ItemViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtCartPrice,txtCartFrameSize;
        ImageView imgCart;
        ImageView imgCartRemove;
        Button btnplaceorder;

        public ItemViewHolder(View itemview)
        {
            super(itemview);

            this.txtCartPrice=itemview.findViewById(R.id.txtCartPrice);
            this.imgCart=itemview.findViewById(R.id.cartCancleImage);
            this.imgCartRemove=itemview.findViewById(R.id.imgCartRemove);
            this.btnplaceorder=itemview.findViewById(R.id.btnPlaceOrder);
            this.txtCartFrameSize=itemview.findViewById(R.id.txtCartFrameSize);
        }
    }
}

