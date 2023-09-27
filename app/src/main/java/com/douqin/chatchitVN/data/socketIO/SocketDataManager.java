package com.douqin.chatchitVN.data.socketIO;

import android.app.Application;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;

public class DataManager {
    private AppDatabase appDatabase;
    public DataManager(AppDatabase appDatabase){
        appDatabase = appDatabase;
    }
}
