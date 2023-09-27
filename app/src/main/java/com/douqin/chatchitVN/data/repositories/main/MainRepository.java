package com.douqin.chatchitVN.data.repositories.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.douqin.chatchitVN.common.StatusIO;
import com.douqin.chatchitVN.network.socketIO.ISession;
import com.douqin.chatchitVN.network.socketIO.SocketIO;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainRepository {

    public static String TAG = MainRepository.class.getSimpleName();

    public MainRepository(Application application) {
        ISession session = SocketIO.gI();
        Object a = session.isConnected()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onChangeStatus);
    }

    public LiveData<String> getStatusApp() {
        return statusCurrentApp;
    }

    private final MutableLiveData<String> statusCurrentApp = new MutableLiveData<>("");

    public void onChangeStatus(StatusIO statusIO) {
        switch (statusIO) {
            case INITIALIZE:
                statusCurrentApp.postValue("");
            case CONNECTED:
                statusCurrentApp.postValue("Đã kết nối");
                Observable.create(emitter ->
                                {
                                    Thread.sleep(2000);
                                    emitter.onNext(new Object());
                                    emitter.onComplete();
                                }
                        ).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(onNext -> {
                            statusCurrentApp.setValue("");
                        }, onError -> {
                            statusCurrentApp.setValue("");
                        });
                break;
            case DISCONNECT:
                statusCurrentApp.postValue("Mất kết nối");
                break;
            case CONNECTING:
                statusCurrentApp.postValue("Đang kết nối");
                break;
            case CONNECT_ERROR:
                statusCurrentApp.postValue("Lỗi kết nối");
                break;
        }
    }

    public void listeningStatusInternet(Boolean isInternet) {
        if (isInternet) {

        } else {
            statusCurrentApp.postValue("Không có kết nối Internet");
        }
    }
}
