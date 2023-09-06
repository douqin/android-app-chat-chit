package com.douqin.chatchitVN.data.repositories.login;

import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.ui.login.cStateLogin;

public interface iLoginRepository {
    void login(String username, String password);

    void signUp();

    LiveData<cStateLogin> getStateLogin();
}
