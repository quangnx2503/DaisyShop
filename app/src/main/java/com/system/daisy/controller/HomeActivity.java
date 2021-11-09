package com.system.daisy.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.system.daisy.R;
import com.system.daisy.common.Constants;
import com.system.daisy.entity.PersonalCartItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home3, R.id.navigation_category, R.id.navigation_search, R.id.navigation_notification, R.id.navigation_personal)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        pref = getSharedPreferences("dataStore", MODE_PRIVATE);

        if (pref != null) {
            Constants.statusLogin.checkLogin = pref.getBoolean("isLogin", false);
            Constants.accountSave.emailAccount = pref.getString("emailAccount", "");
            String serializedObject = pref.getString("jsonListPersonalCart", "");
            ArrayList<PersonalCartItems> listPersonalCartItems = new ArrayList<>();
            if (serializedObject != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<PersonalCartItems>>() {
                }.getType();
                listPersonalCartItems = gson.fromJson(serializedObject, type);
            }
            Constants.personalCart.listPersonalCartItems = listPersonalCartItems == null ? new ArrayList<>() : listPersonalCartItems;
        }
    }

    public void onViewCardClick(View view) {
        Intent intent;
        boolean isLogin = Constants.statusLogin.checkLogin;
        if (isLogin) {
            intent = new Intent(this, ShoppingCartActivity.class);
            intent.putExtra("checkHome", "checkHome");
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
    }
}