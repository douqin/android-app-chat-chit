package com.douqin.chatchitVN.data.socketIO;

import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.network.apis.RemoteData.MessageRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.ReactionRemoteData;
import com.douqin.chatchitVN.network.socketIO.Listener;
import com.douqin.chatchitVN.network.socketIO.SocketIO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.stream.Collectors;

public class SocketDataManager {
    static String TAG = "SocketDataManager";
    private AppDatabase appDatabase;

    private SocketIO socketIO;

    public SocketDataManager(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
        socketIO = SocketIO.gI();
        socketIO.initBaseIO(MeManager.gI().getToken(), "dasdadadasd");
        socketIO.connect();
        this.addEvent();
    }

    private void addEvent() {
        this.socketIO.addListener(saveMessage);
        this.socketIO.addListener(reactMessage);
    }

    private Listener saveMessage = new Listener(
            "message",
            args -> {
                List<MessageRemoteData> remoteData = new Gson().fromJson(args[0].toString(), new TypeToken<List<MessageRemoteData>>() {
                }.getType());
                SocketDataManager.this.appDatabase.messageDao().InsertAll(remoteData.stream().map(
                        MessageRemoteData::toEntity
                ).collect(Collectors.toList()));
            }
    );
    private Listener reactMessage = new Listener(
            "react_message",
            args -> {
                ReactionRemoteData remoteData = new Gson().fromJson(args[0].toString(), ReactionRemoteData.class);
                SocketDataManager.this.appDatabase.reactionDao().insert(
                        remoteData.toEntity()
                );
            }
    );
}
