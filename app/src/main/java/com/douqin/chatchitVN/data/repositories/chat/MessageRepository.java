package com.douqin.chatchitVN.data.repositories.chat;

import android.util.Log;

import com.douqin.chatchitVN.data.local.room.dao.MessageDao;
import com.douqin.chatchitVN.data.local.room.dao.ReactionDao;
import com.douqin.chatchitVN.data.local.room.entity.MessageEntity;
import com.douqin.chatchitVN.data.local.room.entity.ReactionEntity;
import com.douqin.chatchitVN.network.apis.RemoteData.GroupChatRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.MessageRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.ReactionRemoteData;
import com.douqin.chatchitVN.network.apis.Request.ApiMessage;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.ui.message.enums.MessageState;
import com.douqin.chatchitVN.ui.message.enums.MessageType;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageRepository {
    private static final String TAG = "MessageRepository";

    public MessageRepository(MessageDao messageDao, ReactionDao reactionDao) {
        this.messageDao = messageDao;
        this.reactionDao = reactionDao;
        this.registerEmitter();
    }

    private final MessageDao messageDao;
    private final ReactionDao reactionDao;

    private void registerEmitter() {

    }

    public void initMessageOfGroup(@androidx.annotation.NonNull GroupChatRemoteData groupChat) {
        ApiMessage.messageService.getMessageFromGroupID(groupChat.idgroup, "")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<List<MessageRemoteData>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<MessageRemoteData>> listResponseAPI) {
                        List<MessageEntity> messageEntityList = new ArrayList<>();
                        List<ReactionEntity> reactionRemoteData = new ArrayList<>();
                        for (MessageRemoteData messageChat : listResponseAPI.data
                        ) {
                            messageEntityList.add(messageChat.toEntity());
                            reactionRemoteData.addAll(messageChat.reacts.stream().map(ReactionRemoteData::toEntity).collect(Collectors.toList()));
                        }
                        messageDao.InsertAll(messageEntityList);
                        reactionDao.insertAll(reactionRemoteData);
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

    public void sendFileMessage(int idgroup, File message, String mimeType, int idMember) {
        List<MessageEntity> messageEntityList = messageDao.getAllMessageNegative();
        int idMessage = (messageEntityList.size() >= 1 ? messageEntityList.get(0).idMessage - 1 : -1);
        messageDao.Insert(new MessageEntity(idMessage, "Image sending", new Date(), MessageType.TEXT, MessageState.SENDING.getValue(), 0, false, idMember));
        MultipartBody.Part part = MultipartBody.Part.createFormData("files", message.getName(), RequestBody.create(message, MediaType.parse(mimeType)));
        ApiMessage.messageService.sendFileMessage(idgroup, part)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<List<MessageRemoteData>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<MessageRemoteData>> responseAPI) {
                        List<MessageEntity> messageEntityList = new ArrayList<>();
                        for (MessageRemoteData messageChat : responseAPI.data
                        ) {
                            messageEntityList.add(messageChat.toEntity());
                        }
                        messageDao.Delete(idMessage);
                        messageDao.InsertAll(messageEntityList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w(MessageRepository.TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendTextMessage(int idgroup, String message, int idMember) {
        List<MessageEntity> messageEntityList = messageDao.getAllMessageNegative();
        int idMessage = (messageEntityList.size() >= 1 ? messageEntityList.get(0).idMessage - 1 : -1);
        messageDao.Insert(new MessageEntity(idMessage, message, new Date(), MessageType.TEXT, MessageState.SENDING.getValue(), 0, false, idMember));
        RequestBody phoneRequestBody = RequestBody.create(message, MediaType.parse("text/plain"));
        ApiMessage.messageService.sendTextMessage(idgroup, phoneRequestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<MessageRemoteData>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<MessageRemoteData> responseAPI) {
                        messageDao.Delete(idMessage);
                        messageDao.Insert(responseAPI.data.toEntity());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w(MessageRepository.TAG, e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
