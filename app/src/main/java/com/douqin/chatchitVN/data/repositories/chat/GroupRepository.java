package com.douqin.chatchitVN.data.repositories.chat;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.local.room.dao.GroupChatDao;
import com.douqin.chatchitVN.data.local.room.dao.MemberDao;
import com.douqin.chatchitVN.data.local.room.dao.UserDao;
import com.douqin.chatchitVN.data.local.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.local.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.local.room.entity.UserEntity;
import com.douqin.chatchitVN.data.local.room.relationship.GroupAndMemberAndMessageEntity;
import com.douqin.chatchitVN.data.local.room.relationship.GroupChatDataEntity;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.network.apis.RemoteData.GroupChatRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.MemberRemoteData;
import com.douqin.chatchitVN.network.apis.Request.ApiGroup;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GroupRepository {

    static String TAG = "GroupRepository";

    private LiveData<List<GroupEntity>> listGroupChat;

    private final GroupChatDao groupChatDao;

    private final MemberDao memberDao;

    private final UserDao userDao;

    public GroupRepository(GroupChatDao groupChatDao, MemberDao memberDao, UserDao userDao) {
        this.groupChatDao = groupChatDao;
        this.memberDao = memberDao;
        this.userDao = userDao;
        this.registerEmitter();
    }

    public LiveData<List<GroupEntity>> abc() {
        return this.groupChatDao.getListGroupChat();
    }

    public LiveData<List<GroupAndMemberAndMessageEntity>> getListGroupWithLastMessage() {
        return this.groupChatDao.getListGroupWithLastMessage();
    }

    public LiveData<List<GroupEntity>> getListGroupChat() {
        return listGroupChat;
    }

    public void syncWithServer(String time) {
        ApiGroup.groupService.syncWithServer(time).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<ResponseAPI<List<GroupChatRemoteData>>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ResponseAPI<List<GroupChatRemoteData>> dataResponse) {
                List<GroupChatRemoteData> groupChats = dataResponse.data;
                List<GroupEntity> list = new ArrayList<>();
                for (int i = 0; i < groupChats.size(); i++) {
                    list.add(groupChats.get(i).toEntity());
                }
                groupChatDao.insertAllGroup(list);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.w(TAG, Objects.requireNonNull(e.getMessage()));
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public Observable<ResponseAPI<List<GroupChatRemoteData>>> getListGroupFromServer() {
        return ApiGroup.groupService.getAllGroup()
                .doOnNext(responseAPI -> {
                    List<GroupChatRemoteData> groupChats = responseAPI.data;
                    for (int i = 0; i < groupChats.size(); i++) {
                        groupChatDao.insertGroup(groupChats.get(i).toEntity());
                        GroupRepository.this.getAllMemberFromServer(groupChats.get(i).idgroup);
                    }
                });
    }

    private void getAllMemberFromServer(int idgroup) {
        ApiGroup.groupService.getAllMemberFromServer(idgroup)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<List<MemberRemoteData>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<MemberRemoteData>> listResponseAPI) {
                        List<MemberRemoteData> listMember = listResponseAPI.data;
                        List<MemberEntity> memberEntities = new ArrayList<>();
                        List<UserEntity> userEntities = new ArrayList<>();
                        for (MemberRemoteData memberDTO : listMember) {
                            MemberEntity memberDTO1 = memberDTO.getMemberEntity();
                            memberEntities.add(memberDTO1);
                            UserEntity entity = memberDTO.getInformationUserEntity();
                            UserEntity me = MeManager.gI().getMySelf();
                            if (me != null) {
                                if (entity.idUser == me.idUser) {
                                    entity.isMe = true;
                                }
                            }
                            userEntities.add(entity);
                        }
                        memberDao.InsertAll(memberEntities);
                        userDao.insertAll(userEntities);
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

    private void registerEmitter() {

    }

    public LiveData<GroupChatDataEntity> getGrWithMemberAndMessage(int id) {
        return this.groupChatDao.getGrWithMemberAndMessage(id);
    }

    public UserEntity getInformationMember(int idMember) {
        return memberDao.getInformationMember(idMember);
    }

    public MemberEntity getInformationMemberFromUser(int iduser) {
        return memberDao.getInformationMemberFromUser(iduser);
    }
}
