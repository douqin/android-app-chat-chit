package com.dxlampro.appchat;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import JavaCode.Clib.SaveDT;
import JavaCode.Component.MyBroadcastReceiver;
import JavaCode.network.Session_ME;
import JavaCode.networklogic.GlobalMessageHandler;
import JavaCode.networklogic.ReadMessServer;

public class MainViewModel extends AndroidViewModel implements iHandlerApp {
    private MutableLiveData<String> strNotificationApp = new MutableLiveData<>("");
    private MyBroadcastReceiver br;
    public MainViewModel(@NonNull Application application) {
        super(application);
        this.initReceiver();
        this._initNetwork();
        SaveDT.initContext(application);
        ReadMessServer.setHandler(this);
    }

    private void initReceiver() {
        br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        super.getApplication().registerReceiver(br, filter);
    }

    public void _initNetwork() {
        Session_ME.gI().setHandler( GlobalMessageHandler.gI());
        Session_ME.gI().connect("http://192.168.1.9", "3000");
    }
    public LiveData<Boolean> getStatusInternet(){
        return br.getIsInternet();
    }
    public LiveData<String> getNotificaionApp(){
        return strNotificationApp;
    }
    @Override
    protected void onCleared() {
        super.getApplication().unregisterReceiver(br);
        ReadMessServer.setHandler(null);
        super.onCleared();
    }

    @Override
    public void onDisconnectServer() {
        strNotificationApp.postValue("on Disconnect Server");
    }

    @Override
    public void onConnectFail() {
        strNotificationApp.postValue("on Connect Fail");
    }

    @Override
    public void onConnectOK() {
        strNotificationApp.postValue("on Connect OK");
    }

}
