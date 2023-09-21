package com.douqin.chatchitVN.domain;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.douqin.chatchitVN.data.database.room.entity.GroupAndMemberAndMessageEntity;
import com.douqin.chatchitVN.data.models.UI.Member;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.data.repositories.chat.GroupRepository;
import com.douqin.chatchitVN.data.repositories.chat.MessageRepository;
import com.douqin.chatchitVN.domain.entities.GroupAndMemberAndMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GroupUserCase {
    public static String TAG = "GroupUserCase";
    private GroupRepository groupRepository;
    private MessageRepository messageRepository;

    public GroupUserCase(GroupRepository groupRepository, MessageRepository messageRepository) {
        this.groupRepository = groupRepository;
        this.messageRepository = messageRepository;
    }

    public LiveData<List<GroupAndMemberAndMessage>> getListGroupWithLastMessage() {
        LiveData<List<GroupAndMemberAndMessageEntity>> listLiveData = groupRepository.getListGroupWithLastMessage();
        return Transformations.map(listLiveData, groupAndMemberAndMessageEntities -> {
            List<GroupAndMemberAndMessage> list = new ArrayList<>();
            for (GroupAndMemberAndMessageEntity e : groupAndMemberAndMessageEntities) {
                list.add(e.toModel());
            }
            return list;
        });
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

    public User getInformationMember(int idMember) {
        return groupRepository.getInformationMember(idMember).toModel();
    }

    public Member getInformationMemberFromUser(int iduser){
        return groupRepository.getInformationMemberFromUser(iduser).toMemberModel();
    }

}