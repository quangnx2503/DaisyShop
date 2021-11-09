package com.system.daisy.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.entity.Product;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView itemImageView;

    public SearchViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        itemImageView = itemView.findViewById(R.id.itemImageView);
    }

}


public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private Context context;
    private List<Product> products;

    public SearchAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.search_layout_item,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.name.setText(products.get(position).getName());
        Picasso.get().load(products.get(position).getAvatar()).into(holder.itemImageView);
        int productId = products.get(position).getId();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ProductDetailActivity.class);
                intent.putExtra("productId",productId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
