package com.douqin.chatchitVN.common;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateConverter {

    @TypeConverter
    public static String dateToString(Date currentDate) {
        if (currentDate == null) {
            return "";
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(currentDate);
    }

    @TypeConverter
    public static Date stringToDate(String strDate) {
        if (strDate.isEmpty()) {
            return null;
        }
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss 'GMT'Z (z)");
        try {
            return dateFormat.parse(strDate);
        } catch (Exception e) {

            Log.w("D", e.getMessage());
            return new Date();
        }
    }
}
