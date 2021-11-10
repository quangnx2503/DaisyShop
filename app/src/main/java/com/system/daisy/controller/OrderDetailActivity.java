package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.system.daisy.R;
import com.system.daisy.adapter.OrderDetailRecyclerAdapter;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.OrderDAO;
import com.system.daisy.dao.RatingDAO;
import com.system.daisy.entity.CartItem;
import com.system.daisy.entity.Orders;
import com.system.daisy.entity.PaymentMethods;
import com.system.daisy.entity.Productrating;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity implements OrderDetailRecyclerAdapter.OnHandleClickListener {

    ArrayList<CartItem> items;
    OrderDAO dao = new OrderDAO();

    OrderDetailRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Common common = new Common();
        RecyclerView recyclerView = findViewById(R.id.order_detail_recycler_view);
        Intent intent = getIntent();
        int orderID = intent.getIntExtra("orderId", 0);

        // set data for recycler view
        items = dao.getCartItemsByOrder(orderID);

        adapter = new OrderDetailRecyclerAdapter(items, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        TextView textViewOrderID = findViewById(R.id.textViewOrderID);
        TextView textViewOrderDate = findViewById(R.id.textViewOrderDate);
        TextView textViewShipDate = findViewById(R.id.textViewShipDate);
        TextView textViewAddress = findViewById(R.id.textViewAddress);
        TextView textViewPaymentMethod = findViewById(R.id.textViewPaymentMethod);
        TextView textViewStatus = findViewById(R.id.textViewStatus);
        TextView textViewTotalPrice = findViewById(R.id.textViewTotalPrice);

        // get payment method by id
        Orders order = dao.getOrderById(orderID);
        PaymentMethods paymentMethod = dao.getPaymentMethodById(order.getPaymentmethod());

        // set data for activity
        textViewOrderID.setText(Integer.toString(order.getId()));
        textViewOrderDate.setText(common.changeDateToString(order.getOrderTime()));
        textViewShipDate.setText(common.changeDateToString(order.getShipTime()));
        textViewAddress.setText(order.getDestination());
        textViewPaymentMethod.setText(paymentMethod.getName());
        textViewStatus.setText(order.isStatus() == true ? "Successful delivery" : "Shipping");
        textViewTotalPrice.setText(common.formatPrice(order.getTotalPrice()));
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
    public void onRateProductClick(int position) {
        CartItem item = items.get(position);
        final Dialog rateDialog = new Dialog(this);
        rateDialog.setCanceledOnTouchOutside(false);
        rateDialog.setContentView(R.layout.activity_rate_product_dialog);
        ImageButton cancelRateDialog = rateDialog.findViewById(R.id.cancelRateDialog);
        TextView rateProductName = rateDialog.findViewById(R.id.rateProductName);
        ImageView rateProductImage = rateDialog.findViewById(R.id.rateProductImage);
        Button rateConfirmButton = rateDialog.findViewById(R.id.rateConfirmButton);
        Button btnFavouriteConfirm = rateDialog.findViewById(R.id.btnFavourite);
        EditText rateContent = rateDialog.findViewById(R.id.edtCardNumber);
        RatingBar rateBar = rateDialog.findViewById(R.id.rateBar);


        // fill data
        Picasso.get().load(item.getUrl()).into(rateProductImage);
        rateProductName.setText(item.getName());

        cancelRateDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateDialog.dismiss();
            }
        });


        rateConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productId = item.getId();
                String content = rateContent.getText().toString();
                float stars = rateBar.getRating();
                String email = Constants.accountSave.emailAccount;
                dao.rateProduct(new Productrating(productId, email, content, stars));

                Toast toast = Toast.makeText(getApplicationContext(), "Successful rating", Toast.LENGTH_LONG);
                toast.show();
                rateDialog.dismiss();
                recreate();
            }
        });

        rateDialog.show();
    }

    @Override
    public void onViewDetailClick(int position) {
        int productId = items.get(position).getId();
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }
}