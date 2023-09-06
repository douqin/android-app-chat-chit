package com.douqin.chatchitVN.data.network;

import com.douqin.chatchitVN.common.StatusIO;
import com.douqin.chatchitVN.data.apis.BaseConfigAPI;
import com.douqin.chatchitVN.data.repositories.login.dtos.Token;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.socket.client.IO;
import io.socket.client.Socket;

public class Session_ME implements ISession, iBehaviorSocket, iSocketIO {
    private Session_ME() {
        this.connectionSubject = BehaviorSubject.createDefault(StatusIO.INITIALIZE);
    }

    protected static Session_ME instance = new Session_ME();
    private BehaviorSubject<StatusIO> connectionSubject;
    private volatile Socket sc;

    @Override
    public void initBaseIO(Token token, String notificationToken) {
        String url = BaseConfigAPI.BASE_URL;
        Map<String, List<String>> header = new HashMap<>();
        header.put("token", Collections.singletonList("bearer " + token.accessToken));
        header.put("notification", Collections.singletonList(notificationToken));
        IO.Options options = IO.Options.builder()
                .setExtraHeaders(header)
                .build();
        this.sc = IO.socket(URI.create(url), options);
        this.initBaseListener();
    }

    @Override
    public void connect() {
        Disposable disposable = Observable.fromCallable(() -> {
                    this.connectionSubject.onNext(StatusIO.CONNECTING);
                    this.sc.connect();
                    return sc;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socket -> {

                });
    }

    private void initBaseListener() {

        Session_ME.gI().addListener(new Listener(
                Socket.EVENT_CONNECT_ERROR,
                args -> {
                    this.connectionSubject.onNext(StatusIO.CONNECT_ERROR);
                }
        ));
        Session_ME.gI().addListener(new Listener(
                Socket.EVENT_CONNECT,
                args -> {
                    this.connectionSubject.onNext(StatusIO.CONNECTED);
                }
        ));
        Session_ME.gI().addListener(new Listener(
                Socket.EVENT_DISCONNECT,
                args -> {
                    this.connectionSubject.onNext(StatusIO.DISCONNECT);
                }
        ));
    }

    @Override
    public void disconnect() {
        this.sc.disconnect();
    }

    @Override
    public Observable<StatusIO> isConnected() {
        return connectionSubject;
    }

    public static Session_ME gI() {
        return instance;
    }


    @Override
    public void addListeners(List<Listener> listeners) {
        for (Listener listener : listeners) {
            this.sc.on(listener.event, listener.fn);
        }
    }

    @Override
    public void doSendMessage(Object o) {

    }

    public void addListener(Listener listener) {
        this.sc.on(listener.event, listener.fn);
    }
}
