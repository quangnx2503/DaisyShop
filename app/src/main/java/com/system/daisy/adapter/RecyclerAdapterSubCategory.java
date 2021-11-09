package com.system.daisy.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.entity.Subcategories;

import java.util.ArrayList;

public class RecyclerAdapterSubCategory extends RecyclerView.Adapter<RecyclerAdapterSubCategory.ViewHolder> {

    ArrayList<Subcategories> subcategories;
    private OnViewListProductListener onViewListProductListener;

    public RecyclerAdapterSubCategory(ArrayList<Subcategories> subcategories,OnViewListProductListener onViewListProductListener) {
        this.subcategories = subcategories;
        this.onViewListProductListener=onViewListProductListener;
    }

    @NonNull
    @Override
    public RecyclerAdapterSubCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_sub_item,parent,false);
        return new ViewHolder(view,onViewListProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterSubCategory.ViewHolder holder, int position) {
        holder.textView.setText(subcategories.get(position).getName());
        Picasso.get().load(subcategories.get(position).getImageURL()).into(holder.imageLink);
    }

    @Override
    public int getItemCount() {
        return subcategories.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageLink;
        TextView textView;
        OnViewListProductListener onViewListProductListener;
        public ViewHolder(@NonNull View itemView,OnViewListProductListener onViewListProductListener) {
            super(itemView);

            textView=itemView.findViewById(R.id.textViewSubCategory);
            imageLink=itemView.findViewById(R.id.imageViewSubCategory);

            this.onViewListProductListener=onViewListProductListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewListProductListener.onSubCategoryCLick(getAdapterPosition());
        }
    }
    public interface OnViewListProductListener {
        void onSubCategoryCLick(int position);
    }
}
