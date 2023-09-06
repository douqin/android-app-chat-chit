package com.douqin.chatchitVN.data.repositories.chat;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.douqin.chatchitVN.data.apis.Response.Request.ApiMessage;
import com.douqin.chatchitVN.data.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.data.apis.dtos.GroupChatDTO;
import com.douqin.chatchitVN.data.apis.dtos.MessageDTO;
import com.douqin.chatchitVN.data.database.room.dao.MessageDao;
import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.models.MessageChat;
import com.douqin.chatchitVN.data.repositories.user.MeManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MessageRepository {
    private static String TAG = "MessageRepository";

    public MessageRepository(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.registerEmitter();
    }

    private final MessageDao messageDao;

    private void registerEmitter() {

    }

    public void initMessageOfGroup(@androidx.annotation.NonNull GroupChatDTO groupChat) {
        ApiMessage.messgaeService.getMessageFromGroupID(groupChat.idgroup, "")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<List<MessageDTO>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<MessageDTO>> listResponseAPI) {
                        for (MessageDTO messageChat : listResponseAPI.data
                        ) {
                            messageDao.Insert(messageChat.toEntity());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w(TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<MessageChat>> getListMessageWithIdGroup(int id) {
        return Transformations.map(messageDao.getListMessageWithIdGroup(id), groupEntityListMap -> {
            List<MessageEntity> messageEntityList = new ArrayList<>();
            for (List<MessageEntity> messageEntity : groupEntityListMap.values()) {
                messageEntityList.addAll(messageEntity);
            }
            messageEntityList.sort(Comparator.comparing(messageEntity -> messageEntity.createdAt));
            List<MessageChat> messageModelList = new ArrayList<>();
            for (MessageEntity messageChat : messageEntityList) {
                MessageChat messageChatModel = messageChat.toMessageChat();
                messageChatModel.me = MessageRepository.this.messageDao.getIduserFromMessage(messageChatModel.idMember, id) == MeManager.gI().getMySelf().idUser;
                messageModelList.add(messageChatModel);
            }
            return messageModelList;
        });
    }

    public LiveData<Map<MemberEntity, List<MessageEntity>>> getListMessageWithIdGroup2(int id) {
        return messageDao.getListMessageWithIdGroup(id);
    }
}
