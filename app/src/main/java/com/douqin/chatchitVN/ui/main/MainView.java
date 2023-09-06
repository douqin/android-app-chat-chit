package com.douqin.chatchitVN.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainView extends AppCompatActivity {
    private static final String TAG = "Main";
    private ActivityMainBinding activityMainBinding;
    private MainViewModel mainViewModel;

    public static String tokenNotification = SaveDT.loadData("tokenNotification");

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initGame(this);
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
    }

    private void initGame(Context c) {
//        DeviceInfoUtils.initIMEI(getApplicationContext());
        this.setView();
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
        mainViewModel.getStatusApp().observe(this, str -> {
            activityMainBinding.checkInternet.setVisibility(View.VISIBLE);
            if (str.isEmpty()) {
                activityMainBinding.checkInternet.setVisibility(View.GONE);
            }
            activityMainBinding.notificationApp.setText(str);
        });
    }

    public static void loadTokenNoti() {
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                        return;
//                    }
//                    // Get new FCM registration token
//                    String token = task.getResult();
//                    // Log and toast
//                    Log.w(TAG, token);
//                    SaveDT.saveStr("notificationToken", token);
//                });
    }
}
