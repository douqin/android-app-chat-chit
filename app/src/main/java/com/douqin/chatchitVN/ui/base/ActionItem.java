package com.douqin.chatchitVN.ui.base;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ActionItem {
    public Action action;
    public Drawable icon;
    public String title;

   public ActionItem(Action action, Drawable icon, String title){
       this.action = action;
       this.title = title;
       this.icon = icon;
   }
}