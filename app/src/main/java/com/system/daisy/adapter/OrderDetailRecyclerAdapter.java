package com.system.daisy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.OrderDAO;
import com.system.daisy.entity.CartItem;

import java.util.ArrayList;

public class OrderDetailRecyclerAdapter extends RecyclerView.Adapter<OrderDetailRecyclerAdapter.OrderDetailViewHolder> {

    ArrayList<CartItem> items;
    private OnHandleClickListener onHandleClickListener;
    private boolean isRated;
    OrderDAO dao = new OrderDAO();
    Common common = new Common();

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    public OrderDetailRecyclerAdapter(ArrayList<CartItem> items, OnHandleClickListener onHandleClickListener) {
        this.items = items;
        this.onHandleClickListener = onHandleClickListener;
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_detail_item, parent, false);
        return new OrderDetailViewHolder(view, onHandleClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        Picasso.get().load(items.get(position).getUrl()).into(holder.orderDetailItemImage);
        holder.orderDetailItemName.setText(items.get(position).getName());
        holder.orderDetailItemPrice.setText(common.formatPrice((items.get(position).getSale())));
        holder.orderDetailItemQuantity.setText(Integer.toString(items.get(position).getQuantity()));
        int productId = items.get(position).getId();
        String email = Constants.accountSave.emailAccount;
        if (dao.isRatedPosition(email, productId)) {
            holder.rateDetailButton.setClickable(false);
            holder.rateDetailButton.setText("Rated");
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView orderDetailItemImage;
        TextView orderDetailItemName, orderDetailItemPrice, orderDetailItemQuantity;
        OnHandleClickListener onRateProductListener;
        Button rateDetailButton;

        public OrderDetailViewHolder(@NonNull View itemView, OnHandleClickListener onRateProductListener) {
            super(itemView);
            orderDetailItemImage = itemView.findViewById(R.id.orderDetailItemImage);
            orderDetailItemName = itemView.findViewById(R.id.orderItemName);
            orderDetailItemPrice = itemView.findViewById(R.id.cartItemSalePrice);
            orderDetailItemQuantity = itemView.findViewById(R.id.orderItemQuantity);
            rateDetailButton = itemView.findViewById(R.id.rateDetailButton);

            this.onRateProductListener = onRateProductListener;
            rateDetailButton.setOnClickListener(v -> onHandleClickListener.onRateProductClick(getAdapterPosition()));
            orderDetailItemImage.setOnClickListener(v -> onHandleClickListener.onViewDetailClick(getAdapterPosition()));
        }

        @Override
        public void onClick(View v) {
            onRateProductListener.onRateProductClick(getAdapterPosition());
        }
    }

    public interface OnHandleClickListener {
        void onRateProductClick(int position);

        void onViewDetailClick(int position);
    }
}
