package com.douqin.chatchitVN.data.repositories.gif;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.douqin.chatchitVN.network.apis.RemoteData.TenorRemoteData;
import com.douqin.chatchitVN.network.apis.Response.Request.ApiTenor;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GifRepository {
    private static String TAG = "GifRepository";

    public LiveData<TenorRemoteData> getTenorGif() {
        return remoteTenorGif;
    }

    private MutableLiveData<TenorRemoteData> remoteTenorGif = new MutableLiveData<>();

    public void getGifFromKeyword(String keyword) {
        ApiTenor.apiTenor.getGiftFromKeyword(keyword, ApiTenor.keyAPI, ApiTenor.limit)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TenorRemoteData>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull TenorRemoteData tenorRemoteData) {
                        remoteTenorGif.setValue(tenorRemoteData);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.w(GifRepository.TAG, e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
