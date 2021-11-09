package com.system.daisy.controller;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.system.daisy.R;
import com.system.daisy.common.Constants;
import com.system.daisy.dao.UserDAO;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText newPassword, retypeNewPass, code;
    TextView alertPass;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPassword = findViewById(R.id.newPassword);
        retypeNewPass = findViewById(R.id.retypeNewPassword);
        code = findViewById(R.id.codeCheck);
        alertPass = findViewById(R.id.alertPass);
        Intent intent = new Intent();
        intent.getExtras();
    }

    public void onClickChange(View view){
        String password1 = newPassword.getText().toString();
        String password2 = retypeNewPass.getText().toString();
        UserDAO userDAO = new UserDAO();
        if(password1.trim().length() > 0 && password2.trim().length() > 0){
            if(Constants.getRandomNumber.numberCheck == Integer.parseInt(code.getText().toString())){
                if(password1.equals(password2)){
                    if(password1.matches(pattern)){
                        userDAO.updatePassword(password1, Constants.accountSave.emailAccount);
                        Log.d("Email: ", Constants.accountSave.emailAccount );
                        Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(this, "Change Password Success!", Toast.LENGTH_LONG).show();
                    } else {
                        alertPass.setText("Please enter a password that is 8 characters long and contains at least one special character!");
                    }
                } else {
                    alertPass.setText("Please check password or code");
                }
            } else if(Constants.getRandomNumber.numberCheck != Integer.parseInt(code.getText().toString())) {
                Toast.makeText(this, "Random Number is wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            alertPass.setText("Please enter a new password!");
        }
    }
}