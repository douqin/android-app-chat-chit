package com.douqin.chatchitVN.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.data.repositories.login.LoginRepository;
import com.douqin.chatchitVN.data.repositories.login.iLoginRepository;

class LoginViewModel extends AndroidViewModel {
    private final iLoginRepository loginRepository;
    private final MutableLiveData<String> username = new MutableLiveData<>(SaveDT.loadData("username"));
    private final MutableLiveData<String> password = new MutableLiveData<>(SaveDT.loadData("password"));
    private final LiveData<cStateLogin> stateLogin;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository();
        stateLogin = loginRepository.getStateLogin();
    }

    public void login(String username, String password) {
        loginRepository.login(username, password);
        SaveDT.saveStr("username", username);
        SaveDT.saveStr("password", password);
    }

    public void signUp() {

    }

    public LiveData<cStateLogin> getStateLogin() {
        return stateLogin;
    }

    public LiveData<String> getUsername() {
        return username;
    }

    public LiveData<String> getPassword() {
        return password;
    }


}
