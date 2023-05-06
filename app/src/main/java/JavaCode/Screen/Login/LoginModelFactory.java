package JavaCode.Screen.Login;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginModelFactory implements ViewModelProvider.Factory {
    static Application application;

    public LoginModelFactory(Application application) {
        LoginModelFactory.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(LoginModelFactory.application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}