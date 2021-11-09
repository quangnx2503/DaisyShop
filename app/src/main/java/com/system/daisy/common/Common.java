package com.system.daisy.common;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Common {
    public String changeDateToString(Date date) {
        String pattern = "HH:mm dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(date);
        return dateAsString;
    }

    public String formatPrice(int price) {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyInstance.format(price);
    }
}
