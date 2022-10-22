package JavaCode.Component;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.dxlampro.appchat.Main;
import com.dxlampro.appchat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
        super();
    }

    @Override
    public void handleIntent(@NonNull Intent intent) {
        super.handleIntent(intent);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotificationx(remoteMessage);
    }

    public void sendNotificationx(RemoteMessage remoteMessage) {
        try {
            Log.e("getPriority",String.valueOf(remoteMessage.getPriority()));
            Intent intent = new Intent(this, Main.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            Uri uriMp3 = Uri.parse("android.resource://"
                    + getApplicationContext().getApplicationContext().getPackageName() + "/" + R.raw.sound_notification);
            Map<String, String> messageData = remoteMessage.getData();
            String title = messageData.get("title");
            String content = messageData.get("content");
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String chanelID = "DXLAMPRO";

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                @SuppressLint("WrongConstant")
                NotificationChannel channel = new NotificationChannel(
                        chanelID,
                        "DX_NOTI",
                        NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("FROM DXLAMPRO");
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setSound(uriMp3, new AudioAttributes.Builder().build());
                channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                channel.enableVibration(true);
                getSystemService(NotificationManager.class).createNotificationChannel(channel);
//                notificationManager.createNotificationChannel(channel);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Notification.Builder a = new Notification.Builder(this,chanelID);
            }
            NotificationCompat.Builder notiCompatBuilder = new NotificationCompat.Builder(this.getApplicationContext(), chanelID);
            notiCompatBuilder.setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_logo_noti)
                    .setTicker("alo alo")
                    .setContentTitle(title)
                    .setContentText(content)
                    .setContentInfo("info")
                    .setContentIntent(pendingIntent);
            NotificationManagerCompat.from(this).notify(1, notiCompatBuilder.build());
//            notificationManager.notify(0, notiCompatBuilder.build());
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void pushNoti(RemoteMessage remoteMessage){
        try {
            Log.e("getPriority", String.valueOf(remoteMessage.getPriority()));
            Uri uriMp3 = Uri.parse("android.resource://"
                    + super.getApplicationContext().getApplicationContext().getPackageName() + "/" + R.raw.sound_notification);
            Map<String, String> messageData = remoteMessage.getData();
            String title = messageData.get("title");
            String content = messageData.get("content");
            String chanelID = "DXLAMPRO";
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                @SuppressLint("WrongConstant")
                NotificationChannel channel = new NotificationChannel(
                        chanelID,
                        "DX_NOTI",
                        NotificationManager.IMPORTANCE_HIGH);
                channel.setDescription("FROM DXLAMPRO");
                channel.enableLights(true);
                channel.setLightColor(Color.RED);
                channel.setSound(uriMp3, new AudioAttributes.Builder().build());
                channel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                channel.enableVibration(true);
                getSystemService(NotificationManager.class).createNotificationChannel(channel);
            }
                Notification.Builder a = new Notification.Builder(this, chanelID);
                a.setAutoCancel(true)
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.ic_logo_noti)
                        .setTicker("alo alo")
                        .setContentTitle(title)
                        .setContentText(content)
                        .setSubText("info");
                NotificationManagerCompat.from(this).notify(1, a.build());
//            notificationManager.notify(0, notiCompatBuilder.build());
        }
        catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("ID TOKEN ", s);
    }

    @Override
    public void onSendError(@NonNull String s, @NonNull Exception e) {
        super.onSendError(s, e);
    }

    private void sendNotificationx(String messageBody) {
        Intent intent = new Intent(this, Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = "DXLAMPRO";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_logo_noti)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_logo_noti))
                        .setContentTitle("cc")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_missed,
                                "Cancel",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)))
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_outgoing,
                                "OK",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}
