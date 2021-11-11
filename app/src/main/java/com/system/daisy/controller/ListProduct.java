package com.system.daisy.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.daisy.R;
import com.system.daisy.adapter.RecyclerAdapterListProduct;
import com.system.daisy.dao.ProductCategoryDAO;
import com.system.daisy.entity.ProductList;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity implements RecyclerAdapterListProduct.OnViewProductDetailListener {
    ArrayList<ProductList> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        int subCategoryID;
        Intent intent = getIntent();
        subCategoryID = intent.getIntExtra("subcategoryID", -1);
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        products = productCategoryDAO.getListProductBySubCategory(subCategoryID);
        RecyclerView recyclerView = findViewById(R.id.lpRecyclerView);
        RecyclerAdapterListProduct adapter = new RecyclerAdapterListProduct(products,this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
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
    public void onProductClick(int position) {
        int productID= products.get(position).getId();
        Intent intent= new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId",productID);
        startActivity(intent);
    }
}