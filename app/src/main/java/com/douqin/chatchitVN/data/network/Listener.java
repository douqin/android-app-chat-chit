package com.douqin.chatchitVN.data.network;

import io.socket.emitter.Emitter;

public class Listener {
    String event;
    Emitter.Listener fn;

    public Listener(String event, Emitter.Listener fn) {
        this.event = event;
        this.fn = fn;
    }
}
