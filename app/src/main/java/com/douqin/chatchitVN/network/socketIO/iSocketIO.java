package com.douqin.chatchitVN.network.socketIO;

import com.douqin.chatchitVN.data.repositories.login.dtos.Token;

public interface iSocketIO {

    void initBaseIO(Token token, String notificationToken);

    void connect();

    void disconnect();
}
