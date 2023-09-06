package com.douqin.chatchitVN.ui.main;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.common.BroadcastReceiverInternet;
import com.douqin.chatchitVN.common.iSubscriberInternet;
import com.douqin.chatchitVN.data.repositories.main.MainRepository;

public class MainViewModel extends AndroidViewModel implements iSubscriberInternet {
    private final MainRepository mainRepository;
    private final BroadcastReceiverInternet br;

    public MainViewModel(@NonNull Application application) {
        super(application);
        br = new BroadcastReceiverInternet(this);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        application.registerReceiver(br, filter);
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
