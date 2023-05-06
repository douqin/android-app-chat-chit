package JavaCode.networklogic;

import com.google.gson.Gson;

import JavaCode.network.IMessageHandler;
import JavaCode.network.Message;

public class GlobalMessageHandler implements IMessageHandler {
    public static String TAG = "GlobalMessageHandler";
    public static GlobalMessageHandler me;
    private final Gson gson;

    public GlobalMessageHandler() {
        this.gson = new Gson();
    }
    //private GlobalLogicHandler globalLogicHandler;

    public static synchronized GlobalMessageHandler gI() {
        if (GlobalMessageHandler.me == null) {
            GlobalMessageHandler.me = new GlobalMessageHandler();
        }
        return GlobalMessageHandler.me;
    }

    @Override
    public void onMessage(Message message) {

    }
}
