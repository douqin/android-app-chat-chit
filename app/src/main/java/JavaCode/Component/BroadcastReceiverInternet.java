package JavaCode.Component;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import JavaCode.Utils.NetworkUtils;

public class BroadcastReceiverInternet extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    private final iSubscriberInternet subscriberInternet;

    public BroadcastReceiverInternet(iSubscriberInternet subscriberInternet) {
        this.subscriberInternet = subscriberInternet;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (NetworkUtils.isNetworkAvailable(context)) {
                subscriberInternet.listeningStatusInternet(true);
            } else {
                subscriberInternet.listeningStatusInternet(false);
            }
        }
    }
}
