package com.douqin.chatchitVN.ui.relationship.friends;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.paging.ExperimentalPagingApi;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;

import com.douqin.chatchitVN.data.local.room.entity.FriendEntity;
import com.douqin.chatchitVN.data.repositories.friends.FriendsRemoteMediator;

@ExperimentalPagingApi
public class FriendViewModel extends AndroidViewModel {
    UserDao userDao = database.userDao();
    Pager<Integer, FriendEntity> pager = Pager(
            new PagingConfig(/* pageSize = */ 10),
            null, // initialKey,
            new FriendsRemoteMediator(query, database, networkService)() -> userDao.pagingSource(query));
    public FriendViewModel(@NonNull Application application) {
        super(application);
    }
}
