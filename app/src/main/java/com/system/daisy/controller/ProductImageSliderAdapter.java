package com.system.daisy.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.system.daisy.R;

import java.util.List;

public class ProductImageSliderAdapter extends SliderViewAdapter<SliderViewHolder> {

    Context context;
    List<String> imageSliderModelList;

    public ProductImageSliderAdapter(Context context, List<String> imageSliderModelList) {
        this.context = context;
        this.imageSliderModelList = imageSliderModelList;
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_layout,parent,false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
       /// viewHolder.sliderImageView.setImageResource(imageSliderModelList.get(position).getImage());
        Picasso.get().load(imageSliderModelList.get(position)).into(viewHolder.sliderImageView);
    }

    @Override
    public int getCount() {
        return imageSliderModelList.size();
    }
}
