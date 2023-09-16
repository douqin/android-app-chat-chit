package com.douqin.chatchitVN.network.socketIO;

import java.util.List;

public interface iBehaviorSocket {
    void doSendMessage(Object o);

    void addListener(Listener listeners);

    void addListeners(List<Listener> listeners);
}
