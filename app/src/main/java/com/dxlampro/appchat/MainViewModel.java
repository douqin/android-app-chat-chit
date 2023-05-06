package com.dxlampro.appchat;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;

import JavaCode.Clib.SaveDT;
import JavaCode.Component.BroadcastReceiverInternet;
import JavaCode.Component.iSubscriberInternet;
import JavaCode.Repository.Main.MainRepository;
import JavaCode.Repository.User.UserManager;

public class MainViewModel extends AndroidViewModel implements iSubscriberInternet {
    private MainRepository mainRepository;
    private BroadcastReceiverInternet br;

    public MainViewModel(@NonNull Application application) {
        super(application);
        br = new BroadcastReceiverInternet(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        application.registerReceiver(br, filter);
        SaveDT.initContext(application.getApplicationContext());
        mainRepository = new MainRepository(application);
    }

    public LiveData<String> getStatusApp() {
        return mainRepository.getStatusApp();
    }

    @Override
    protected void onCleared() {
        this.getApplication().unregisterReceiver(br);
        super.onCleared();
    }

    @Override
    public void listeningStatusInternet(Boolean isInternet) {
        this.mainRepository.listeningStatusInternet(isInternet);
    }
}
