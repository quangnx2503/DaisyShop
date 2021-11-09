package com.system.daisy.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.entity.Product;

import java.util.List;

//Xử lý việc đưa itemList vào trong imageView
public class TopProductAdapter extends RecyclerView.Adapter<TopProductAdapter.TopSaleProductViewHolder> {

    Context context;
    List<Product> productsList;

    public TopProductAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override  //Code cho nay la gi nhi?
    public TopSaleProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.topproduct_row_items,parent,false);
        return new TopSaleProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSaleProductViewHolder holder, int position) {
        //holder.topSaleImageView.setImageResource(productsList.get(position).getAvatar());
        Picasso.get().load(productsList.get(position).getAvatar()).into(holder.topSaleImageView);
        int productId = productsList.get(position).getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetailActivity.class);
                intent.putExtra("productId",productId);
                intent.putExtra("checkHome","checkHome");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
        //return 5;
    }

    public static class TopSaleProductViewHolder extends RecyclerView.ViewHolder{

        ImageView topSaleImageView;

        public TopSaleProductViewHolder(@NonNull View itemView) {
            super(itemView);

            topSaleImageView = itemView.findViewById(R.id.categoryImage);
        }
    }
}
