package JavaCode.network;

import org.json.JSONException;

public interface IMessageHandler {

        void onConnectOK();

        void onConnectionFail();

        void onDisconnected();

        void onMessage(Message message) throws JSONException;
}
