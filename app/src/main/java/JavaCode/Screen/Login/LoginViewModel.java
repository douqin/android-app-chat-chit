package JavaCode.Screen.Login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import JavaCode.Clib.SaveDT;
import JavaCode.Repository.Login.LoginRepository;
import JavaCode.Repository.Login.iLoginRepository;

class LoginViewModel extends AndroidViewModel {
    private iLoginRepository loginRepository;
    private MutableLiveData<String> username = new MutableLiveData<>(SaveDT.loadData("username"));
    private MutableLiveData<String> password = new MutableLiveData<>(SaveDT.loadData("password"));
    private LiveData<cStateLogin> stateLogin;

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
