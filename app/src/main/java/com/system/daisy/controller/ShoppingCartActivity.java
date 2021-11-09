package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.system.daisy.R;
import com.system.daisy.adapter.CartRecyclerAdapter;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;
import com.system.daisy.entity.CartItem;
import com.system.daisy.entity.PersonalCartItems;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements CartRecyclerAdapter.OnHandleCartItemListener {

    PersonalCartItems pCart;
    int totalCost;
    Common common = new Common();
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        RecyclerView recyclerView = findViewById(R.id.cartRecyclerView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        email = Constants.accountSave.emailAccount;
        pCart = Constants.personalCart.getCartOfUser(email);
        ArrayList<CartItem> items = pCart.getCartItems();

        totalCost = Constants.personalCart.totalCost(email);

        if (items == null || items.size() == 0) {
            Intent intent = new Intent(this, EmptyCartActivity.class);
            startActivity(intent);
        }

        CartRecyclerAdapter adapter = new CartRecyclerAdapter(items, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        TextView numberItemInCart = findViewById(R.id.numberItemInCart);
        if (pCart.getCartItems() != null) {
            numberItemInCart.setText(Integer.toString(pCart.getCartItems().size()));
        } else {
            numberItemInCart.setText(Integer.toString(0));
        }
        TextView textProvisionalPrice = findViewById(R.id.textProvisionalPrice);
        textProvisionalPrice.setText(common.formatPrice(totalCost));
        TextView textTotalPrice = findViewById(R.id.totalCost);
        textTotalPrice.setText(common.formatPrice(totalCost));
    }

    public void onNextStepOrderClick(View view) {
        if (Constants.personalCart.cartQuantity(email) != 0) {
            Intent intent = new Intent(this, ShippingAddressActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have no item in cart", Toast.LENGTH_LONG);
            toast.show();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                String checkHome = getIntent().getStringExtra("checkHome");
                if (checkHome != null) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveCartToSharedData(PersonalCartItems pCart) {
        Gson gson = new Gson();
        Constants.personalCart.updateCart(pCart, email);
        String json = gson.toJson(Constants.personalCart.listPersonalCartItems);
        SharedPreferences prefs = getSharedPreferences("dataStore", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("jsonListPersonalCart", json);
        editor.commit();
    }

    @Override
    public void onDecreaseQuantityClick(int position) {
        int quantity = pCart.getCartItems().get(position).getQuantity();
        if (quantity != 1) {
            pCart.getCartItems().get(position).setQuantity(quantity - 1);
        } else {
            pCart.getCartItems().remove(position);
        }
        saveCartToSharedData(pCart);
        recreate();
    }

    @Override
    public void onIncreaseQuantityClick(int position) {
        int quantity = pCart.getCartItems().get(position).getQuantity();
        pCart.getCartItems().get(position).setQuantity(quantity + 1);
        saveCartToSharedData(pCart);
        recreate();
    }

    @Override
    public void onDeleteClick(int position) {
        pCart.getCartItems().remove(position);
        saveCartToSharedData(pCart);
        recreate();
    }

    @Override
    public void onViewDetailClick(int position) {
        int productId = pCart.getCartItems().get(position).getId();
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String checkHome = getIntent().getStringExtra("checkHome");
        if (checkHome != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}