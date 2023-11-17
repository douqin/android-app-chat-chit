package com.douqin.chatchitVN.data.repositories.friends;

import androidx.annotation.NonNull;
import androidx.paging.ExperimentalPagingApi;
import androidx.paging.LoadType;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxRemoteMediator;

import com.douqin.chatchitVN.data.local.room.dao.FriendDao;
import com.douqin.chatchitVN.data.local.room.dao.UserDao;
import com.douqin.chatchitVN.data.local.room.database.AppDatabase;
import com.douqin.chatchitVN.data.local.room.entity.FriendEntity;
import com.douqin.chatchitVN.data.models.UI.Friend;
import com.douqin.chatchitVN.data.models.UI.User;
import com.douqin.chatchitVN.network.apis.RemoteData.InviteRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.InvitesResponseRemoteData;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.HttpException;

@ExperimentalPagingApi
public class FriendsRemoteMediator extends RxRemoteMediator<Integer, Friend> {

    private String query;
    private RelationShipRepository relationShipRepository;
    private AppDatabase database;
    private FriendDao friendDao;

    FriendsRemoteMediator(
            String query,
            RelationShipRepository relationShipRepository, AppDatabase database
    ){
        this.query = query;
        this.relationShipRepository = relationShipRepository;
        this.database = database;
        this.friendDao = database.friendDao();
    }
    @NonNull
    @Override
    public Single<MediatorResult> loadSingle(@NonNull LoadType loadType, @NonNull PagingState<Integer, Friend> state) {
        int loadKey = 0;
        switch (loadType) {
            case REFRESH:
                break;
            case PREPEND:
                return Single.just(new MediatorResult.Success(true));
            case APPEND:
                Friend lastItem = state.lastItemOrNull();
                if (lastItem == null) {
                    return Single.just(new MediatorResult.Success(true));
                }
                loadKey = lastItem.id;
                break;
        }
        return relationShipRepository.getFriends(query, loadKey)
                .subscribeOn(Schedulers.io())
                .map((Function<InvitesResponseRemoteData, MediatorResult>) response -> {
                    database.runInTransaction(() -> {
                        if (loadType == LoadType.REFRESH) {
//                            friendDao.deleteByQuery(query);
                        }
                        List<FriendEntity> a = response.invites.stream().map(InviteRemoteData::toFriendEntity).collect(Collectors.toList());
                        friendDao.insertAll(a);
                    });
                    return new MediatorResult.Success(response.nextCursor == null);
                })
                .onErrorResumeNext(e -> {
                    if (e instanceof IOException || e instanceof HttpException) {
                        return Single.just(new MediatorResult.Error(e));
                    }
                    return Single.error(e);
                });
    }

//    @NotNull
//    @Override
//    public Single<InitializeAction> initializeSingle() {
//        long cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
//        return mUserDao.lastUpdatedSingle()
//                .map(lastUpdatedMillis -> {
//                    if (System.currentTimeMillis() - lastUpdatedMillis <= cacheTimeout) {
//                        // Cached data is up-to-date, so there is no need to re-fetch
//                        // from the network.
//                        return InitializeAction.SKIP_INITIAL_REFRESH;
//                    } else {
//                        // Need to refresh cached data from network; returning
//                        // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
//                        // APPEND and PREPEND from running until REFRESH succeeds.
//                        return InitializeAction.LAUNCH_INITIAL_REFRESH;
//                    }
//                });
//    }
}
