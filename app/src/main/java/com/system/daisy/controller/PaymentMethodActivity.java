package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.system.daisy.R;
import com.system.daisy.common.Common;
import com.system.daisy.common.Constants;

public class PaymentMethodActivity extends AppCompatActivity {

    int totalCost = Constants.personalCart.totalCost(Constants.accountSave.emailAccount);
    Intent intent;
    Bundle bundle;
    RadioButton rbtnCard;
    RadioButton rbtnCash;
    RadioButton rbtnTransfer;
    Common common = new Common();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        TextView textTotalPrice = findViewById(R.id.totalCost);
        textTotalPrice.setText(common.formatPrice(totalCost));
        rbtnCard = findViewById(R.id.rbtnCard);
        rbtnCash = findViewById(R.id.rbtnCash);
        rbtnTransfer = findViewById(R.id.rbtnTransfer);
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            switch (bundle.getInt("paymentMethod")) {
                case 1:
                    rbtnCard.setChecked(true);
                    break;
                case 2:
                    rbtnCash.setChecked(true);
                    break;
                case 3:
                    rbtnTransfer.setChecked(true);
                    break;
            }
        }
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
        Intent intentChangeActivity;

        if (rbtnCard.isChecked()) {
            intentChangeActivity = new Intent(this, PayCardActivity.class);
            bundle.putInt("paymentMethod", 1);
        } else {
            intentChangeActivity = new Intent(this, OrderConfirmActivity.class);
            if (rbtnCash.isChecked()) {
                bundle.putInt("paymentMethod", 2);
            } else if (rbtnTransfer.isChecked()) {
                bundle.putInt("paymentMethod", 3);
            }
        }
        intentChangeActivity.putExtras(bundle);
        startActivity(intentChangeActivity);
    }
}