package com.douqin.chatchitVN.application;

import android.app.Application;

import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.ui.main.MainView;

public class ChatChitApplication extends Application {

    String TAG = "ChatChitApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        SaveDT.initContext(this);
        MainView.loadTokenNoti();
    }
}
