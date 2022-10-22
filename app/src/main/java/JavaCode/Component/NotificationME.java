package JavaCode.Component;

public class NotificationME {
//    private static final String  TAG = gI().getClass().getName();
//    private static NotificationME instance;
    /**
// token client: enjLTmzoSbSFWGz7w0V5mq:APA91bE6upm55O0WaSWRO5iOjiReuc_sL3vcOxdtyAGQRwVAEACLc0Ii7lezPc30vpXLCrzxHwe8FdQKFikD1CijGt6SnsQOl_1BwXLUQ1I58E2NKCksY0REahdLe7ZJ8QCYeTXNBXwN
    // token cloud: AAAAQNxh2fI:APA91bFn19nwD5OYB3zf4ClUcBKPuP7EbjeRuZlR-s0Seo94_ACde6wd6DNzCiz5g9LkPAX7plZNBY6DSeHcmcKn9p80g3FtD1SH4w57rsLTfCcqiBpRfvvP9W7ZaovWF7kdCMY-v2cS
*/
    private NotificationME(){
    }

//    public void init() {
//        createNotification();
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//                        // Get new FCM registration token
//                        String token = task.getResult();
//                        Log.d(TAG, token);
//                        Toast.makeText(Main.gI().getApplicationContext(), token, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    public static NotificationME gI(){
//        if(instance != null)
//            return instance;
//        return instance = new NotificationME();
//    }
    private static final String CHANNEL_ID = "CHANNEL ID 1";
    private static final int NOTIFICATION_ID = 101;
//    public void sendNotification(String title, String name) {
//        createNotification();
//        NotificationCompat.Builder notification = new NotificationCompat.Builder(Main.gI().getApplicationContext(),CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_logo_noti)
//                .setContentTitle(title)
//                .setContentText(name);
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Main.gI().getApplicationContext());
//        notificationManager.notify(NOTIFICATION_ID, notification.build());
//    }

//    private void createNotification() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = Main.gI().getString(R.string.channel_name);
//            String description = Main.gI().getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
//            channel.setDescription(description);
//            channel.setSound(Uri.parse("android.resource://"
//                    + Main.gI().getApplicationContext().getPackageName() + "/" + R.raw.sound_notification),new AudioAttributes.Builder().build());
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = Main.gI().getSystemService(NotificationManager.class);
//            if(notificationManager != null){
//                notificationManager.createNotificationChannel(channel);
//            }
//        }
//    }
}
