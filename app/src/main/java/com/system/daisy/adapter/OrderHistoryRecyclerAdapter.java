package com.system.daisy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.entity.Orders;

import java.util.ArrayList;

public class OrderHistoryRecyclerAdapter extends RecyclerView.Adapter<OrderHistoryRecyclerAdapter.OrderHisViewHolder> {

    ArrayList<Orders> orderItems;
    ArrayList<String> productNames;
    Common common = new Common();
    private OnViewDetailListener mOnViewDetailListener;

    public OrderHistoryRecyclerAdapter(ArrayList<Orders> OrderItems, ArrayList<String> productNames, OnViewDetailListener onViewDetailListener) {
        this.orderItems = OrderItems;
        this.productNames = productNames;
        this.mOnViewDetailListener = onViewDetailListener;
    }

    @NonNull
    @Override
    public OrderHisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.order_history_item, parent, false);
        return new OrderHisViewHolder(view, mOnViewDetailListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHisViewHolder holder, int position) {
        holder.orderHisProductsName.setText(productNames.get(position));
        holder.orderHisId.setText(Integer.toString(orderItems.get(position).getId()));
        holder.orderHisOrderDate.setText(common.changeDateToString(orderItems.get(position).getOrderTime()));
        String status = orderItems.get(position).isStatus() == true ? "Successful delivery" : "Shipping";
        holder.orderHisStatus.setText(status);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class OrderHisViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView orderHisProductsName, orderHisId, orderHisOrderDate, orderHisStatus;
        Button viewDetailButton;
        OnViewDetailListener onViewDetailListener;

        public OrderHisViewHolder(@NonNull View itemView, OnViewDetailListener onViewDetailListener) {
            super(itemView);
            orderHisProductsName = itemView.findViewById(R.id.order_history_item_name);
            orderHisId = itemView.findViewById(R.id.order_history_item_id);
            orderHisOrderDate = itemView.findViewById(R.id.order_history_item_order_date);
            orderHisStatus = itemView.findViewById(R.id.order_history_item_status);
            viewDetailButton = itemView.findViewById(R.id.viewDetailButton);

            this.onViewDetailListener = onViewDetailListener;
            viewDetailButton.setOnClickListener(this);
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

