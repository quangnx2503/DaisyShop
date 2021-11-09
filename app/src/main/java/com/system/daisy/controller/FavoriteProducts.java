package com.system.daisy.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.adapter.RecyclerAdapterFavoriteProducts;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.ProductTDAO;
import com.system.daisy.entity.FavoriteProduct;

import java.util.ArrayList;

public class FavoriteProducts extends AppCompatActivity implements RecyclerAdapterFavoriteProducts.OnViewProductFavoriteListener {


    ArrayList<FavoriteProduct> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_products);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        String email = Constants.accountSave.emailAccount;

        RecyclerView recyclerView = findViewById(R.id.fpRecyclerView);
        ProductTDAO productTDAO = new ProductTDAO();
        TextView fpNotification= findViewById(R.id.fpNotification);
        products = productTDAO.getListProductFavorite(email);
        if(products.size()==0){
            fpNotification.setText("No favorite products");
        }else {
            fpNotification.setText("");
        }
        RecyclerAdapterFavoriteProducts adapter = new RecyclerAdapterFavoriteProducts(products, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewProductFavoriteClick(int position) {
        int productId = products.get(position).getId();
        Intent intent= new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId",productId);
        startActivity(intent);
    }
}