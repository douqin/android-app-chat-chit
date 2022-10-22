package JavaCode.Screen.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import JavaCode.Clib.SaveDT;
import JavaCode.networklogic.ReadMessServer;
import JavaCode.networklogic.Service;

class LoginViewModel extends AndroidViewModel implements iLogin,iResultLogin {
    private MutableLiveData<String> username = new MutableLiveData<>(SaveDT.loadData("username"));
    private MutableLiveData<String> password = new MutableLiveData<>(SaveDT.loadData("password"));
    private MutableLiveData<String> textStatusLogin = new MutableLiveData<>("");
    private MutableLiveData<StateLogin> stateLogin = new MutableLiveData<>(StateLogin.NO_STATE);
    public LoginViewModel(@NonNull Application application) {
        super(application);
        ReadMessServer.setLogin(this);
    }

    @Override
    public void login(String username, String password) {
        stateLogin.setValue(StateLogin.NO_STATE);
        textStatusLogin.setValue("");
        Service.gI().Login(username,password);
        SaveDT.saveStr("username",username);
        SaveDT.saveStr("password",password);
    }

    @Override
    public void signUp() {

    }

    @Override
    public LiveData<StateLogin> getStateLogin() {
        return stateLogin;
    }

    @Override
    public LiveData<String> getUsername() {
        return username;
    }

    @Override
    public LiveData<String> getPassword() {
        return password;
    }

    @Override
    public LiveData<String> getTextStatusLogin() {
        return textStatusLogin;
    }

    @Override
    protected void onCleared()  {
        ReadMessServer.setLogin(null);
        super.onCleared();
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


}
