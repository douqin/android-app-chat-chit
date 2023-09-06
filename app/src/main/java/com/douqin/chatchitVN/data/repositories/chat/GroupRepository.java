package com.douqin.chatchitVN.data.repositories.chat;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.douqin.chatchitVN.data.apis.Response.Request.ApiGroup;
import com.douqin.chatchitVN.data.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.data.apis.dtos.GroupChatDTO;
import com.douqin.chatchitVN.data.apis.dtos.MemberDTO;
import com.douqin.chatchitVN.data.database.room.dao.GroupChatDao;
import com.douqin.chatchitVN.data.database.room.dao.MemberDao;
import com.douqin.chatchitVN.data.database.room.entity.GroupEntity;
import com.douqin.chatchitVN.data.database.room.entity.MemberEntity;
import com.douqin.chatchitVN.data.models.GroupChat;

import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GroupRepository {

    static String TAG = "GroupRepository";

    private final LiveData<List<GroupEntity>> listGroupChat;

    private final MutableLiveData<GroupChat> currentGr;

    public LiveData<GroupChat> getCurrentGr() {
        return currentGr;
    }

    private final GroupChatDao groupChatDao;

    private final MemberDao memberDao;

    public GroupRepository(GroupChatDao groupChatDao, MemberDao memberDao) {
        this.groupChatDao = groupChatDao;
        this.memberDao = memberDao;
        this.registerEmitter();
        listGroupChat = groupChatDao.getListGroupChat();
        currentGr = new MutableLiveData<>(null);
    }

    public LiveData<List<GroupEntity>> getListGroupChat() {
        return listGroupChat;
    }

    public void openChat(GroupChat groupChat) {
        currentGr.setValue(groupChat);
    }

    public void syncWithServer(String time) {
        ApiGroup.groupService.syncWithServer(time).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<ResponseAPI<List<GroupChatDTO>>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull ResponseAPI<List<GroupChatDTO>> dataResponse) {
                List<GroupChatDTO> groupChats = dataResponse.data;
                for (int i = 0; i < groupChats.size(); i++) {
                    groupChatDao.insertGroup(groupChats.get(i).toEntity());
                }
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

    public Observable<ResponseAPI<List<GroupChatDTO>>> getListGroupFromServer() {
        return ApiGroup.groupService.getAllGroup()
                .doOnNext(responseAPI -> {
                    List<GroupChatDTO> groupChats = responseAPI.data;
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
                .subscribe(new Observer<ResponseAPI<List<MemberDTO>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<MemberDTO>> listResponseAPI) {
                        List<MemberDTO> listMember = listResponseAPI.data;
                        for (MemberDTO memberDTO : listMember) {
                            MemberEntity memberDTO1 = memberDTO.getMemberEntity();
                            memberDao.Insert(memberDTO1);
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

    private void registerEmitter() {

    }

}
