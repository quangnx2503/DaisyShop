package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.system.daisy.R;

public class ShippingAddressActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    EditText edtShipAddress;
    EditText edtShipName;
    EditText edtShipPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_adress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        edtShipAddress = findViewById(R.id.edtShipAddress);
        edtShipName = findViewById(R.id.edtShipName);
        edtShipPhone = findViewById(R.id.edtShipPhone);
        intent = getIntent();
        bundle = intent.getExtras();
        if (bundle != null) {
            edtShipAddress.setText(bundle.getString("edtShipAddress"));
            edtShipName.setText(bundle.getString("edtShipName"));
            edtShipPhone.setText(bundle.getString("edtShipPhone"));
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

    public void onConfirmAddressClick(View view) {
        Intent intent = new Intent(this, PaymentMethodActivity.class);
        Bundle bundle = new Bundle();

        String name = edtShipName.getText().toString();
        String address = edtShipAddress.getText().toString();
        String phone = edtShipPhone.getText().toString();
        String regexPhone = "\\d+";

        if (name.equals("") || address.equals("") || phone.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please fill all input", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            if (!phone.matches(regexPhone) && !(phone.equals(""))) {
                Toast toast = Toast.makeText(getApplicationContext(), "Phone number must be only digits", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                bundle.putString("edtShipAddress", edtShipAddress.getText().toString());
                bundle.putString("edtShipName", edtShipName.getText().toString());
                bundle.putString("edtShipPhone", edtShipPhone.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }
}