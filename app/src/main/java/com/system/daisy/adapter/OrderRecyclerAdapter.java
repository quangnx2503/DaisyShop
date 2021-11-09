package com.system.daisy.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
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
import com.system.daisy.entity.CartItem;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.CartViewHolder> {

    ArrayList<CartItem> items;
    private OnViewDetailListener OnViewDetailListener;
    Common common = new Common();

    public OrderRecyclerAdapter(ArrayList<CartItem> items, OnViewDetailListener onViewDetailListener) {
        this.items = items;
        this.OnViewDetailListener = onViewDetailListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_item, parent, false);
        return new CartViewHolder(view, OnViewDetailListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Picasso.get().load(items.get(position).getUrl()).into(holder.orderItemImage);
        holder.orderItemName.setText(items.get(position).getName());
        //set price string strikeThroughSpan
        SpannableString priceString = new SpannableString(common.formatPrice(items.get(position).getPrice()));
        priceString.setSpan(new StrikethroughSpan(), 0, priceString.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.orderItemPrice.setText(priceString);
        holder.orderItemQuantity.setText(Integer.toString(items.get(position).getQuantity()));
        holder.orderItemSalePrice.setText(common.formatPrice(items.get(position).getSale()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView orderItemImage;
        TextView orderItemName, orderItemPrice, orderItemQuantity, orderItemSalePrice;
        OnViewDetailListener onViewDetailListener;

        public CartViewHolder(@NonNull View itemView, OnViewDetailListener onViewDetailListener) {
            super(itemView);
            orderItemImage = itemView.findViewById(R.id.orderDetailItemImage);
            orderItemName = itemView.findViewById(R.id.orderItemName);
            orderItemSalePrice = itemView.findViewById(R.id.cartItemSalePrice);
            orderItemQuantity = itemView.findViewById(R.id.orderItemQuantity);
            orderItemPrice = itemView.findViewById(R.id.orderItemPrice);
            this.onViewDetailListener = onViewDetailListener;
            orderItemImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewDetailListener.onViewDetailClick(getAdapterPosition());
        }
    }

    public interface OnViewDetailListener {
        void onViewDetailClick(int position);
    }
}
