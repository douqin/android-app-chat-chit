package com.dxlampro.appchat;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class MainViewModelFactory implements ViewModelProvider.Factory {
    static Application application;
    public MainViewModelFactory(Application application) {
        MainViewModelFactory.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(MainViewModelFactory.application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}