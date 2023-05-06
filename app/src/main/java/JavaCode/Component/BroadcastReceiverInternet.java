package JavaCode.Component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import androidx.lifecycle.MutableLiveData;

import JavaCode.Clib.NetworkME;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";

    public MutableLiveData<Boolean> getIsInternet() {
        return isInternet;
    }

    private MutableLiveData<Boolean> isInternet = new MutableLiveData<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (NetworkME.isNetworkAvailable(context)) {
                isInternet.setValue(true);
            } else {
                isInternet.setValue(false);
            }
        }
    }
}
