package JavaCode.Screen.Splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface iAuth {
    void auth();
    LiveData<StateAuth> getStateAuth();
}
