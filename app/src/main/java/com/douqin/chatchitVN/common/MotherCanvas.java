package com.douqin.chatchitVN.common;

import android.content.Context;
import android.util.DisplayMetrics;

public class MotherCanvas {
    public static int height = -1;
    public static int width = -1;

    public static void init(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }
}
