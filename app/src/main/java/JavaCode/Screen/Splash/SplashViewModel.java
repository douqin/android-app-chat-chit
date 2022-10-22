package JavaCode.Screen.Splash;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import JavaCode.networklogic.ReadMessServer;
import JavaCode.networklogic.Service;

public class SplashViewModel extends AndroidViewModel implements iAuth,iAuthResult {
    private MutableLiveData<StateAuth> stateAuth = new MutableLiveData<>(StateAuth.NO_STATE);
    public SplashViewModel(@NonNull Application application) {
        super(application);
        ReadMessServer.setAuth(this);
    }
    @Override
    public void auth(){
        Service.gI().auth();
    }

    @Override
    protected void onCleared() {
        ReadMessServer.setAuth(null);
        super.onCleared();
    }

    @Override
    public LiveData<StateAuth> getStateAuth() {
        return stateAuth;
    }

    @Override
    public void authSuccessfully() {
        stateAuth.postValue(StateAuth.SUCCESSFULLY);
    }

    @Override
    public void authError(String msg) {
        stateAuth.postValue(StateAuth.ERROR);
    }
}
