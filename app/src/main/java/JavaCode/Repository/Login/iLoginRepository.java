package com.Repository.Login;

import androidx.lifecycle.LiveData;

import JavaCode.DataLocal.Screen.Login.StateLogin;

public interface iLoginRepository {
    public void login(String username, String password);
    public void signUp();
    public LiveData<String> getTextStatusLogin();
    public LiveData<StateLogin> getStateLogin();
}
