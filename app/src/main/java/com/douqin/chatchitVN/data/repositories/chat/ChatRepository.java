package com.douqin.chatchitVN.data.repositories.chat;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.data.database.room.dao.GroupChatDao;
import com.douqin.chatchitVN.data.database.room.dao.MemberDao;
import com.douqin.chatchitVN.data.database.room.dao.MessageDao;
import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.models.GroupChat;
import com.douqin.chatchitVN.data.network.Session_ME;
import com.douqin.chatchitVN.data.network.iSocketIO;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChatRepository {

    private static String TAG = "ChatRepository";
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;


    public ChatRepository(GroupChatDao groupChatDao, MessageDao messageDao, MemberDao memberDao) {
        iSocketIO socketIO = Session_ME.gI();
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        return;
//                    }
//                    // Get new FCM registration token
//                    String token = task.getResult();
//                    // Log and toast
//                    SaveDT.saveStr("notificationToken", token);
//                    socketIO.initBaseIO(MeManager.gI().getToken(), token);
//                    socketIO.connect();
//                    //TODO: toi uu
//                });
//        socketIO.initBaseIO(MeManager.gI().getToken(), "token");
//        socketIO.connect();
        // FIXME: connect server socketIO
        this.groupRepository = new GroupRepository(groupChatDao, memberDao);
        this.messageRepository = new MessageRepository(messageDao);
    }

    public LiveData<GroupChat> getCurrentGr() {
        return groupRepository.getCurrentGr();
    }

    public LiveData<List<GroupChat>> getListGroupChatFromLocal() {
        return Transformations.map(
                groupRepository.getListGroupChat(), e -> {
                    List<GroupChat> groupChats = new ArrayList<>();
                    for (GroupEntity entity : e) {
                        GroupChat groupChat = entity.toModel();
                        groupChat.messageChatList = messageRepository.getListMessageWithIdGroup(groupChat.idgroup);
                        groupChats.add(groupChat);
                    }
                    return groupChats;
                });
    }

    public void openChat(GroupChat groupChat) {
        this.groupRepository.openChat(groupChat);
    }

    @SuppressLint("CheckResult")
    public void initBaseData() {
        groupRepository.getListGroupFromServer()
                .flatMap(groups -> Observable.fromIterable(groups.data))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(group -> {
                    messageRepository.initMessageOfGroup(group);
                }, error -> {
                    Log.e(TAG, Objects.requireNonNull(error.getMessage()));
                });
    }

    public void syncWithServer() {
    }
}
