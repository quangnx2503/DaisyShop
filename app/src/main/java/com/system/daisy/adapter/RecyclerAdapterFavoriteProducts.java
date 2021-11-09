package com.system.daisy.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.entity.FavoriteProduct;

import java.util.ArrayList;

public class RecyclerAdapterFavoriteProducts extends RecyclerView.Adapter<RecyclerAdapterFavoriteProducts.ViewHolder> {
    ArrayList<FavoriteProduct> products;
    private OnViewProductFavoriteListener onViewProductFavoriteListener;

    public RecyclerAdapterFavoriteProducts(ArrayList<FavoriteProduct> products, OnViewProductFavoriteListener onViewProductFavoriteListener) {
        this.products = products;
        this.onViewProductFavoriteListener = onViewProductFavoriteListener;
    }

    @NonNull
    @Override
    public RecyclerAdapterFavoriteProducts.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_product_item, parent, false);
        return new ViewHolder(view, onViewProductFavoriteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterFavoriteProducts.ViewHolder holder, int position) {
        holder.fpName.setText(products.get(position).getName());
        Common common= new Common();
        Picasso.get().load(products.get(position).getImageURL()).into(holder.fpImage);

        if (products.get(position).getSale() != 0) {
            int price = products.get(position).getPrice();
            String priceFact = "<strike>" + common.formatPrice(products.get(position).getPrice()) + "</strike>";
            holder.fpPrice.setText(android.text.Html.fromHtml(priceFact));

            int sale = products.get(position).getSale();
            holder.fpSale.setText("-" + Integer.toString(sale) + "%");
            holder.fpSale.setTextColor(Color.RED);

            String priceSale = common.formatPrice((int) Math.ceil((price - price * sale / 100) / 1000) * 1000);
            holder.fpPriceSale.setText(priceSale);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView fpImage;
        TextView fpName, fpPrice,fpSale,fpPriceSale;
        OnViewProductFavoriteListener onViewProductFavoriteListener;

        public ViewHolder(@NonNull View itemView, OnViewProductFavoriteListener onViewProductFavoriteListener) {
            super(itemView);
            fpName = itemView.findViewById(R.id.fpName);
            fpPrice = itemView.findViewById(R.id.fpPrice);
            fpImage = itemView.findViewById(R.id.fpImage);
            fpSale=itemView.findViewById(R.id.fpSale);
            fpPriceSale=itemView.findViewById(R.id.fpRealPrice);

            this.onViewProductFavoriteListener = onViewProductFavoriteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewProductFavoriteListener.onViewProductFavoriteClick(getAdapterPosition());
        }
    }

    public interface OnViewProductFavoriteListener {
        void onViewProductFavoriteClick(int position);
    }
}
