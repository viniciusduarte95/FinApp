package com.example.finapp;

import android.widget.EditText;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static long dateToMili(Date date){
        return date.getTime();
    }

    public static Date miliToDate(long mili){
        return new Date(mili);
    }

    public static Date stringToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.parse(date);
    }

    public static String formatValor(double valor){
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMinimumFractionDigits(2);
        nf  .setMaximumFractionDigits(2);
        return "R$ " + nf.format(valor);
    }
}
