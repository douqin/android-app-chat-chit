package com.douqin.chatchitVN.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import com.douqin.chatchitVN.R;
import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.ui.main.MainView;

public class MyApplication extends Application {
    public static final String CHANEL_ID = "push_notification_id";

    @Override
    public void onCreate() {
        super.onCreate();
        SaveDT.initContext(this);
        createChanelID();
        MainView.loadTokenNoti();
    }

    private void createChanelID() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANEL_ID, "Push Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(Uri.parse("android.resource://"
                    + this.getApplicationContext().getPackageName() + "/" + R.raw.sound_notification_2), (new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build()));
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
