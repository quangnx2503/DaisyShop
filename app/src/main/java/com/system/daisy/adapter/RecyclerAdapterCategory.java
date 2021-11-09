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
import com.system.daisy.entity.Categories;

import java.util.ArrayList;

public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.ViewHolder> {

    ArrayList<Categories> categories;
    private OnViewSubCategoryListener onViewSubCategoryListener;

    public RecyclerAdapterCategory(ArrayList<Categories> categories, OnViewSubCategoryListener onViewSubCategoryListener) {
        this.categories = categories;
        this.onViewSubCategoryListener = onViewSubCategoryListener;
    }

    @NonNull
    @Override
    public RecyclerAdapterCategory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view, onViewSubCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterCategory.ViewHolder holder, int position) {
        holder.textView.setText(categories.get(position).getName());
        Picasso.get().load(categories.get(position).getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView textView;
        OnViewSubCategoryListener onViewSubCategoryListener;

        public ViewHolder(@NonNull View itemView, OnViewSubCategoryListener onViewSubCategoryListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewCategory);
            imageView = itemView.findViewById(R.id.imageViewCategory);

            this.onViewSubCategoryListener = onViewSubCategoryListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewSubCategoryListener.onViewSubCategoryClick(getAdapterPosition());
        }
    }

    public interface OnViewSubCategoryListener {
        void onViewSubCategoryClick(int position);
    }
}
