//package com.douqin.chatchitVN.common;
//
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//import java.util.Map;
//
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//    private static final String TAG = "MyFirebaseMessagingService";
//
//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage message) {
//        super.onMessageReceived(message);
//        Map<String, String> stringMap = message.getData();
//        String myAnotherKey = stringMap.get("data");
//        try {
//
//        } catch (Exception e) {
//            Log.e(TAG, e.toString());
//        }
//    }
//
//
//    @Override
//    public void onNewToken(@NonNull String token) {
//        super.onNewToken(token);
//        SaveDT.saveStr("tokenNotification", token);
//        Log.e(TAG, token);
//    }
//
//}