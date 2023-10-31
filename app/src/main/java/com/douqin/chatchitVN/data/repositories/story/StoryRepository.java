package com.douqin.chatchitVN.data.repositories.story;

import androidx.lifecycle.LiveData;

import com.douqin.chatchitVN.data.local.room.dao.StoryDao;
import com.douqin.chatchitVN.data.local.room.relationship.UserWithStoryRelation;
import com.douqin.chatchitVN.network.apis.RemoteData.StoryRemoteData;
import com.douqin.chatchitVN.network.apis.Request.ApiStory;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StoryRepository {
    StoryDao storyDao;

    public StoryRepository(StoryDao storyDao) {
        this.storyDao = storyDao;
    }

    public LiveData<List<UserWithStoryRelation>> getStory() {
        return storyDao.getStoryFriend();
    }

    public void getStoryFromServer() {
        ApiStory.storyService.getAllStoryFromFriends()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<List<StoryRemoteData>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<List<StoryRemoteData>> listResponseAPI) {
                        StoryRepository.this.storyDao.InsertAll(listResponseAPI.data.stream().map(StoryRemoteData::toEntity).collect(Collectors.toList()));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
