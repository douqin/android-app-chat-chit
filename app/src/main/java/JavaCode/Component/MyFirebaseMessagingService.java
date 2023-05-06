package JavaCode.Component;

import static JavaCode.Application.MyApplication.CHANEL_ID;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.Person;
import androidx.core.app.RemoteInput;

import com.dxlampro.appchat.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Map;

import JavaCode.Clib.SaveDT;
import JavaCode.Model.dto.Cmd;
import JavaCode.Model.dto.Cmd3;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMessagingService";
    static Gson gson = new Gson();
    ArrayList<Cmd3> listMessage = new ArrayList<>();
    public int idgroup = -1;
    int idOldGroup = -1;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
//        RemoteMessage.Notification notification = message.getNotification();
//        if(notification != null){
//            String title = notification.getTitle();
//            String content = notification.getBody();
//            sendNotification(title,content );
//        }
        Map<String, String> stringMap = message.getData();
        String myAnotherKey = stringMap.get("data");
        try {
            JsonElement jsonElement = JsonParser.parseString(myAnotherKey);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            Cmd _cmd = MyFirebaseMessagingService.gson.fromJson(jsonArray.get(0), Cmd.class);
            JsonElement data = jsonArray.get(1);
            handlerDataFCM(_cmd.cmd, data);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

    }

    private void handlerDataFCM(int cmd, JsonElement data) {
        switch (cmd) {
            case 3:
                try {
                    Cmd3 dataCmd = MyFirebaseMessagingService.gson.fromJson(data, Cmd3.class);
                    sendNotification(dataCmd);
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                break;
        }
    }

    private void sendNotification(Cmd3 dataCMD) {
        String namegr = dataCMD.group.name;
        String nameUser = dataCMD.user.name;
        String message = dataCMD.message;
        idgroup = dataCMD.group.idgroup;

        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Reply")
                .build();
        Intent sendIntent = new Intent(this, DirectReplyReceiver.class);
        PendingIntent sendPendingIntent = PendingIntent.getBroadcast(this, 0, sendIntent, 0);
        NotificationCompat.Action actionSend = new NotificationCompat.Action.Builder(R.drawable.iconsend, "Reply", sendPendingIntent).addRemoteInput(remoteInput).build();
        Person sender = new Person.Builder()
                .setName(nameUser + ": ")
                .setImportant(true)
                .build();
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(sender);
        messagingStyle.setConversationTitle("Nhóm: " + namegr);
        messagingStyle.setGroupConversation(true);
        if (idgroup == idOldGroup) {
            listMessage.add(dataCMD);
            for (Cmd3 _dataCMD : listMessage) {
                Person _sender = new Person.Builder()
                        .setName(_dataCMD.user.name + ":")
                        .setImportant(true)
                        .build();
                messagingStyle.addMessage(new NotificationCompat.MessagingStyle.Message(_dataCMD.message, System.currentTimeMillis(), _sender));
            }
        } else {
            if (listMessage.size() > 0)
                listMessage.clear();
            listMessage.add(dataCMD);
            messagingStyle.addMessage(new NotificationCompat.MessagingStyle.Message(message, System.currentTimeMillis(), sender));
        }

        idOldGroup = idgroup;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID)
                .setSmallIcon(R.drawable.logo_spl_src)
                .setShowWhen(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(messagingStyle)
                .addAction(actionSend)
                .setAutoCancel(true)
                .setSound(Uri.parse("android.resource://" + this.getApplicationContext().getPackageName() + "/" + R.raw.sound_message));
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(idgroup, notification);
        }

    }

    private void _sendNotification(Cmd3 dataCMD) {
        String namegr = dataCMD.group.name;
        String nameUser = dataCMD.user.name;
        String message = dataCMD.message;
        int idgroup = dataCMD.group.idgroup;
        RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
                .setLabel("Reply")
                .build();
        Person sender = new Person.Builder()
                .setName(nameUser + ": ")
                .setImportant(true)
                .build();
        NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(sender);
        messagingStyle.setConversationTitle("Nhóm: " + namegr);
        messagingStyle.addMessage(new NotificationCompat.MessagingStyle.Message(message, System.currentTimeMillis(), sender));
        messagingStyle.setGroupConversation(true);
//        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.layout_notificaion_2);
//        notificationLayout.setTextViewText(R.id.name_group,namegr);
//        notificationLayout.setTextViewText(R.id.usernameNoti,nameUser);
//        notificationLayout.setTextViewText(R.id.messageChatNoti,message);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID)
//                .setCustomContentView(notificationLayout)
                .setSmallIcon(R.drawable.logo_spl_src)
                .setShowWhen(true)
                .setStyle(messagingStyle)
                .setSound(Uri.parse("android.resource://"
                        + this.getApplicationContext().getPackageName() + "/" + R.raw.sound_message));
        Notification notification = builder
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(1, notification);
        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        SaveDT.saveStr("tokenNotification", token);
        Log.e(TAG, token);
    }

    private boolean getActiveNotificaion(int idNotification) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        StatusBarNotification[] notifications = mNotificationManager.getActiveNotifications();
        for (StatusBarNotification notification : notifications) {
            if (notification.getId() == idNotification) {
                return true;
            }
        }
        return false;
    }
}