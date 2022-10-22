package com.dxlampro.appchat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dxlampro.appchat.databinding.ActivityMainBinding;

import java.util.Objects;

public class Main extends AppCompatActivity {
    private static final String TAG = "Main";
    private static final int CALL_PHONE = 10;
    public static String chanelID = "noti";
    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initGame(this);
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
    }

    private void createNotiChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(chanelID, "Update", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);
        }
    }

    private void initGame(Context c) {
        this.setView();
        this.createNotiChanel();
        this._initViewModel();
    }

    private void setView() {
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    @SuppressLint("SetTextI18n")
    private void _initViewModel() {
        mainViewModel = new ViewModelProvider(this, new MainViewModelFactory(this.getApplication())).get(MainViewModel.class);
        mainViewModel.getStatusInternet().observe(this, isInternet -> {
            if (isInternet)
                activityMainBinding.notificationApp.post(() -> {
                    activityMainBinding.checkInternet.setVisibility(View.VISIBLE);
                    activityMainBinding.notificationApp.setVisibility(View.VISIBLE);
                    activityMainBinding.notificationApp.setText("ONLINE...");
                });
            else {
                activityMainBinding.checkInternet.setVisibility(View.VISIBLE);
                activityMainBinding.notificationApp.setVisibility(View.VISIBLE);
                activityMainBinding.notificationApp.setText("NO INTERNET ...");
            }
        });
        mainViewModel.getNotificaionApp().observe(this, str -> {
            activityMainBinding.notificationApp.setText(str);
        });
    }
}
//    public void Install(){
//        Intent i = new Intent();
//        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//      //  i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setAction(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.fromFile(new File(DownLoadApp.pathFileApp)), "application/vnd.android.package-archive" );
//        Log.d("Lofting", "About to install new .apk");
//        Main.gI().getApplicationContext().startActivity(i);
//    }