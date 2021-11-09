package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.OrderDAO;
import com.system.daisy.entity.CartItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderConfirmActivity extends AppCompatActivity {

    int totalCost = Constants.personalCart.totalCost(Constants.accountSave.emailAccount);
    Intent intent;
    Bundle bundle;
    OrderDAO dao = new OrderDAO();
    Common common = new Common();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        TextView textTotalPrice = findViewById(R.id.totalCost);
        textTotalPrice.setText(common.formatPrice(totalCost));
        intent = getIntent();
        bundle = intent.getExtras();

        TextView orderCfName = findViewById(R.id.orderCfName);
        if (bundle.getString("edtShipName") != null) {
            orderCfName.setText(bundle.getString("edtShipName"));
        }
        TextView orderCfAddress = findViewById(R.id.orderCfAddress);
        if (bundle.getString("edtShipAddress") != null) {
            orderCfAddress.setText(bundle.getString("edtShipAddress"));
        }
        TextView orderCfMethod = findViewById(R.id.orderCfMethod);
        int pId = bundle.getInt("paymentMethod");
        orderCfMethod.setText(dao.getPaymentMethodById(pId).getName());
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

    public void onNextStepOrderClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to order ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast = Toast.makeText(getApplicationContext(), "Order successfully", Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(OrderConfirmActivity.this, HomeActivity.class);
                startActivity(intent);
                //insert order
                String email = Constants.accountSave.emailAccount;
                Date orDate = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(orDate);
                c.add(Calendar.DATE, 3);
                Date shipDate = c.getTime();
                String address = bundle.getString("edtShipAddress");
                int totalPrice = Constants.personalCart.totalCost(email);
                int status = 0;
                int payMethod = bundle.getInt("paymentMethod");
                ArrayList<CartItem> items = Constants.personalCart.getCartOfUser(email).getCartItems();
                dao.insertOrder(email, orDate, shipDate, address, totalPrice, status, payMethod, items);
                dao.insertOrderNotification(email, orDate, "Order notification", "Order successfully at " + common.changeDateToString(orDate) + ", Please keep attention at your phone to receive goods. Thanks you");
                Constants.personalCart.removeCartOfUser(email);
                Gson gson = new Gson();
                String json = gson.toJson(Constants.personalCart.listPersonalCartItems);
                SharedPreferences prefs = getSharedPreferences("dataStore", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("jsonListPersonalCart", json);
                editor.commit();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }

    public void onOrderToHomeClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void changeOrderOnClick(View view) {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }

    public void changeAddressOnClick(View view) {
        Intent intent = new Intent(this, ShippingAddressActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void changeMethodOnClick(View view) {
        Intent intent = new Intent(this, PaymentMethodActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}