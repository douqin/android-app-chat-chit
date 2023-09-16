package com.douqin.chatchitVN.network.socketIO;

import com.douqin.chatchitVN.common.StatusIO;

import io.reactivex.rxjava3.core.Observable;

public interface ISession {
    Observable<StatusIO> isConnected();
}
