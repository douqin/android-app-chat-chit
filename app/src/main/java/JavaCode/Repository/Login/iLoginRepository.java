package JavaCode.Repository.Login;

import androidx.lifecycle.LiveData;

import JavaCode.Screen.Login.cStateLogin;

public interface iLoginRepository {
    void login(String username, String password);

    void signUp();

    LiveData<cStateLogin> getStateLogin();
}
