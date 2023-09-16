package com.douqin.chatchitVN.data.repositories.chat;

import android.util.Log;

import com.douqin.chatchitVN.data.database.room.dao.MessageDao;
import com.douqin.chatchitVN.data.database.room.entity.MessageEntity;
import com.douqin.chatchitVN.network.apis.RemoteData.GroupChatRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.MessageRemoteData;
import com.douqin.chatchitVN.network.apis.Response.Request.ApiMessage;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageRepository {
    private static String TAG = "MessageRepository";

    public MessageRepository(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.registerEmitter();
    }

    private final MessageDao messageDao;

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
                        for (MessageRemoteData messageChat : listResponseAPI.data
                        ) {
                            messageEntityList.add(messageChat.toEntity());
                        }
                        messageDao.InsertAll(messageEntityList);
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

    public void sendFileMessage(int idgroup, File message, String mimeType) {
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

    public void sendTextMessage(int idgroup, String message) {
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
