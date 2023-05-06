package JavaCode.network;

import io.socket.emitter.Emitter;

public class Listener {
    String event;
    Emitter.Listener fn;

    Listener(String event, Emitter.Listener fn) {
        this.event = event;
        this.fn = fn;
    }
}
