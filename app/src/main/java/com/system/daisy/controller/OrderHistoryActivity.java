package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.system.daisy.R;
import com.system.daisy.adapter.OrderHistoryRecyclerAdapter;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.OrderDAO;
import com.system.daisy.entity.Orders;
import com.system.daisy.entity.Products;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryRecyclerAdapter.OnViewDetailListener {

    ArrayList<Orders> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

//        String email = Constants.accountSave.emailAccount;

        OrderDAO orderDAO = new OrderDAO();
        orders = orderDAO.getOrdersByEmail(Constants.accountSave.emailAccount);

        ArrayList<String> productNames = new ArrayList<>();
        for (Orders order : orders) {
            ArrayList<Products> products = orderDAO.getProductsByOrder(order.getId());
            productNames.add(orderDAO.getNameOfListProducts(products));
        }

        RecyclerView recyclerView = findViewById(R.id.orderHisRecyclerView);
        OrderHistoryRecyclerAdapter adapter = new OrderHistoryRecyclerAdapter(orders, productNames, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
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
    public void onViewDetailClick(int position) {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        try {
            intent.putExtra("orderId", orders.get(position).getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}