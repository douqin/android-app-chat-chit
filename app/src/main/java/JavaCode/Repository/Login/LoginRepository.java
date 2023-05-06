package com.Repository.Login;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import JavaCode.networklogic.ReadMessServer;
import JavaCode.networklogic.Service;
import JavaCode.DataLocal.Screen.Login.StateLogin;
import JavaCode.DataLocal.Screen.Login.iResultLogin;

public class LoginRepository implements iLoginRepository, iResultLogin {
    private MutableLiveData<String> textStatusLogin = new MutableLiveData<>("");


    private MutableLiveData<StateLogin> stateLogin = new MutableLiveData<>(StateLogin.NO_STATE);
    public LoginRepository(Application application){
        ReadMessServer.setLogin(this);
    }

    @Override
    public void login(String username, String password) {
        stateLogin.setValue(StateLogin.NO_STATE);
        textStatusLogin.setValue("");
        Service.gI().Login(username, password);
    }

    @Override
    public void signUp() {

    }

    @Override
    public void loginSuccess() {
        stateLogin.postValue(StateLogin.SUCCESSFULLY);
    }

    @Override
    public void loginError(String strMessage) {
        stateLogin.postValue(StateLogin.ERROR);
        textStatusLogin.postValue(strMessage);
    }

    public LiveData<String> getTextStatusLogin(){
        return textStatusLogin;
    }

    public LiveData<StateLogin> getStateLogin() {
        return stateLogin;
    }

}
