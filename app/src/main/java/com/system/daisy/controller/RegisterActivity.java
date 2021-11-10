package com.system.daisy.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.system.daisy.R;
import com.system.daisy.dao.UserDAO;
import com.system.daisy.entity.User;

import java.text.ParseException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText txtName, txtPhoneNumber, txtEmail, txtPassword, txtDate;
    RadioButton rbMale, rbFeMale, rbSex;
    Button btnSelectDate;
    RadioGroup sexGroup;
    CheckBox checkValid;
    boolean sexCheck;
    boolean checkValueSec;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        txtPassword = findViewById(R.id.txtPassword);
        txtDate = findViewById(R.id.txtDate);
        checkValid = findViewById(R.id.checkValid);
        rbMale = findViewById(R.id.rdMale);
        rbFeMale = findViewById(R.id.rdFemale);
        sexGroup = findViewById(R.id.groupSex);
        btnSelectDate = findViewById(R.id.btnSelectDate);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickRegister(View view) throws ParseException {
        String name = txtName.getText().toString();
        String email = txtEmail.getText().toString();
        String phoneNumber = txtPhoneNumber.getText().toString();
        String password = txtPassword.getText().toString();
        String dob = txtDate.getText().toString();
        int radioId = sexGroup.getCheckedRadioButtonId();
        rbSex = findViewById(radioId);
        if(rbSex.getText().equals("Male")){
            sexCheck = true;
        } else {
            sexCheck = false;
        }
        checkValueSec = checkValid.isChecked();
        if(name.length() < 4){
            Toast.makeText(this, "Please input name again!", Toast.LENGTH_LONG).show();
            return;
        }

        String regexPhone = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        if(!phoneNumber.matches(regexPhone)){
            Toast.makeText(this, "Please check phone number again!", Toast.LENGTH_LONG).show();
            return;
        }

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches() == false){
            Toast.makeText(this, "The email wrong!", Toast.LENGTH_LONG).show();
            return;
        }

        String regexPass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if(!password.matches(regexPass)){
            Toast.makeText(this, "Please enter a password that is 8 characters long and contains at least one special character!", Toast.LENGTH_LONG).show();
            return;
        }

        if(dob.equals("")){
            Toast.makeText(this, "Please select date of birth!", Toast.LENGTH_LONG).show();
            return;
        }

        if(checkValueSec == false){
            Toast.makeText(this, "Please accept the terms of use!!", Toast.LENGTH_LONG).show();
            return;
        }


        UserDAO userDAO = new UserDAO();
        try {
            if(userDAO.checkExist(email)){
                Toast.makeText(this, "Email is already in use. Please input again!", Toast.LENGTH_LONG).show();
                return;
            }

            User u = new User();
            u.setName(name);
            u.setEmail(email);
            u.setPass_word(password);
            u.setGender(sexCheck);
            u.setPhone(phoneNumber);
            u.setDob(dob);
            userDAO.insert(u);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showDate(View view){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                }
            }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}