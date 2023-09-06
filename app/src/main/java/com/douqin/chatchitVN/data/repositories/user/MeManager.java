package com.douqin.chatchitVN.data.repositories.user;

import android.app.Application;

import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.data.apis.dtos.UserDTO;
import com.douqin.chatchitVN.data.database.room.dao.MySelfDao;
import com.douqin.chatchitVN.data.database.room.database.AppDatabase;
import com.douqin.chatchitVN.data.database.room.entity.UserEntity;
import com.douqin.chatchitVN.data.repositories.login.dtos.Token;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MeManager {

    private Token token;

    private MeManager() {
        this.initToken();
    }

    private void initToken() {
        Gson gson = new Gson();
        this.token = gson.fromJson(SaveDT.loadData("token"), Token.class);
    }

    public UserEntity getMySelf() {
        return me;
    }

    private UserEntity me;

    private Observable<UserEntity> mySelf;
    private static MeManager instance;
    private MySelfDao mySelfDao;

    public void initializeDAO(Application context) throws Exception {
        mySelfDao = AppDatabase.gI(context).mySelfDao();
        if (mySelfDao.getCount() != 1) {
            throw new Exception("Không tìm thấy dữ liệu bản thân");
        } else {
            mySelf = mySelfDao.getMe();
            mySelf.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserEntity>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull UserEntity userEntity) {
                            MeManager.this.me = userEntity;
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
//            mySelf = Transformations.map(
//                    mySelfDao.getMe(), UserEntity::toModel);
        }
    }

    public void setToken(Token token) {
        Gson gson = new Gson();
        SaveDT.saveStr("token", gson.toJson(token));
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    public static synchronized MeManager gI() {
        if (instance == null) {
            return instance = new MeManager();
        }
        return instance;
    }

    public void setMySelf(UserDTO user) {
        UserEntity userEntity = user.toUserEntity();
        userEntity.isMe = true;
        this.mySelfDao.setMe(userEntity);
        mySelf = mySelfDao.getMe();
        mySelf.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserEntity userEntity) {
                        MeManager.this.me = userEntity;
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
