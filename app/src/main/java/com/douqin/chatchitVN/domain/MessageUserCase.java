package com.douqin.chatchitVN.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.douqin.chatchitVN.data.database.room.entity.GroupChatWithMemberAndMessageEntity;
import com.douqin.chatchitVN.data.models.UI.GroupChatWithMemberAndMessage;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.data.repositories.chat.GroupRepository;
import com.douqin.chatchitVN.data.repositories.chat.MessageRepository;
import com.douqin.chatchitVN.data.repositories.gif.GifRepository;
import com.douqin.chatchitVN.network.apis.RemoteData.TenorRemoteData;

import java.io.File;

public class MessageUserCase {
    private GifRepository gifRepository;
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;

    public MessageUserCase(GroupRepository groupRepository, MessageRepository messageRepository, GifRepository gifRepository) {
        this.gifRepository = gifRepository;
        this.groupRepository = groupRepository;
        this.messageRepository = messageRepository;
    }

    public LiveData<GroupChatWithMemberAndMessage> getGrWithMemberAndMessage(int id) {
        LiveData<GroupChatWithMemberAndMessageEntity> memberAndMessageEntityLiveData = groupRepository.getGrWithMemberAndMessage(id);
        return Transformations.map(
                memberAndMessageEntityLiveData, GroupChatWithMemberAndMessageEntity::toModel
        );
    }

    public void getGifFromKeyword(String keyword) {
        this.gifRepository.getGifFromKeyword(keyword);
//        return Transformations.map(this.gifRepository.getTenorGif(), gif->{
//            return TenorRemoteData;
//        });
    }

    public LiveData<TenorRemoteData> getNewGif() {
        return this.gifRepository.getTenorGif();
    }

    public User getInformationMember(int idMember) {
        return null;
    }

    public void sendMessage(int idgroup, File message, String mimeType) {
        messageRepository.sendFileMessage(idgroup, message, mimeType);
    }

    public void sendMessage(int idgroup, String message) {
        messageRepository.sendTextMessage(idgroup, message);
    }
}
