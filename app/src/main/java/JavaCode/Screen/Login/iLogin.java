package JavaCode.Screen.Login;

import androidx.lifecycle.LiveData;

public interface iLogin {
    LiveData<StateLogin> getStateLogin();
    LiveData<String> getUsername();
    LiveData<String> getPassword();
    LiveData<String> getTextStatusLogin();
    void login(String username, String password);
    void signUp();
}
