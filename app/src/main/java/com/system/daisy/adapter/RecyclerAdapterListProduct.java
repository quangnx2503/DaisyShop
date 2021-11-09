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
import com.system.daisy.entity.ProductList;

import java.util.ArrayList;

public class RecyclerAdapterListProduct extends RecyclerView.Adapter<RecyclerAdapterListProduct.ViewHolder> {

    ArrayList<ProductList> products;
    private OnViewProductDetailListener onViewProductDetailListener;

    public RecyclerAdapterListProduct(ArrayList<ProductList> products, OnViewProductDetailListener onViewProductDetailListener) {
        this.products = products;
        this.onViewProductDetailListener = onViewProductDetailListener;
    }

    @NonNull
    @Override
    public RecyclerAdapterListProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product_item, parent, false);
        return new ViewHolder(view, onViewProductDetailListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterListProduct.ViewHolder holder, int position) {
        holder.lpName.setText(products.get(position).getName());
        Common common = new Common();
        Picasso.get().load(products.get(position).getImageUrl()).into(holder.lpImage);

        if (products.get(position).getSale() != 0) {
            int price = products.get(position).getPrice();
            String priceFact = "<strike>" + common.formatPrice(products.get(position).getPrice()) + "</strike>";
            holder.lpPrice.setText(android.text.Html.fromHtml(priceFact));

            int sale = products.get(position).getSale();
            holder.lpSale.setText("-" + Integer.toString(sale) + "%");
            holder.lpSale.setTextColor(Color.RED);

            String priceSale = common.formatPrice((int) Math.ceil((price - price * sale / 100) / 1000) * 1000);
            holder.lpPriceSale.setText(priceSale);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView lpImage;
        TextView lpName, lpPrice, lpSale, lpPriceSale;
        OnViewProductDetailListener onViewProductDetailListener;

        public ViewHolder(@NonNull View itemView, OnViewProductDetailListener onViewProductDetailListener) {
            super(itemView);
            lpName = itemView.findViewById(R.id.lpName);
            lpPrice = itemView.findViewById(R.id.lpPrice);
            lpImage = itemView.findViewById(R.id.lpImageView);
            lpSale = itemView.findViewById(R.id.lpSale);
            lpPriceSale = itemView.findViewById(R.id.lpPriceSale);


            this.onViewProductDetailListener = onViewProductDetailListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewProductDetailListener.onProductClick(getAdapterPosition());
        }
    }

    public interface OnViewProductDetailListener {
        void onProductClick(int position);
    }
}
