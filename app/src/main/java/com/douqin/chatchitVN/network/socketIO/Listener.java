package com.douqin.chatchitVN.network.socketIO;

import io.socket.emitter.Emitter;

public class Listener {
    String event;
    Emitter.Listener fn;

    public Listener(String event, Emitter.Listener fn) {
        this.event = event;
        this.fn = fn;
    }
}
