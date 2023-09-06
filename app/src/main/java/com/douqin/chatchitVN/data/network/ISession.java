package com.douqin.chatchitVN.data.network;

import com.douqin.chatchitVN.common.StatusIO;

import io.reactivex.rxjava3.core.Observable;

public interface ISession {
    Observable<StatusIO> isConnected();
}
