package com.douqin.chatchitVN.common;

import android.content.Context;
import android.util.DisplayMetrics;

public abstract class MotherCanvas {
    public static int height = -1;
    public static int width = -1;

    public MotherCanvas(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }
}
