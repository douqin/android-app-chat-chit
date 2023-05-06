package JavaCode.Component;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.RemoteInput;

public class DirectReplyReceiver extends BroadcastReceiver {
    static String TAG = DirectReplyReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        if (bundle != null) {
            CharSequence replyText = bundle.getCharSequence("key_text_reply");
            Toast.makeText(context.getApplicationContext(), replyText, Toast.LENGTH_LONG).show();
        }
        try {
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        } catch (Exception e) {
            Toast.makeText(context.getApplicationContext(), TAG + "Error get", Toast.LENGTH_LONG).show();
        }
    }

}
