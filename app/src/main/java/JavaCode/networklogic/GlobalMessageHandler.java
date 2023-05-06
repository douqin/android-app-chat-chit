package JavaCode.Component.networklogic;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import JavaCode.Clib.SaveDT;
import JavaCode.Model.GroupChat;
import JavaCode.Model.User;
import JavaCode.Model.dto.Token;
import JavaCode.network.IMessageHandler;
import JavaCode.network.Message;

public class GlobalMessageHandler implements IMessageHandler {
    public static String TAG = "GlobalMessageHandler";
    public static GlobalMessageHandler me;
    private ReadMessServer readMessServer;
    private Gson gson;

    public GlobalMessageHandler() {
        this.readMessServer = new ReadMessServer();
        this.gson = new Gson();
    }
    //private GlobalLogicHandler globalLogicHandler;

    public static GlobalMessageHandler gI() {
        if (GlobalMessageHandler.me == null) {
            GlobalMessageHandler.me = new GlobalMessageHandler();
        }
        return GlobalMessageHandler.me;
    }

    @Override
    public void onConnectOK() {

    }

    @Override
    public void onConnectionFail() {

    }

    @Override
    public void onDisconnected() {
        readMessServer.onDisconnected();
    }


    @Override
    public void onMessage(Message message) {
        Log.e("onMessage", "--------------------------------------");
        Log.e("cmd:", String.valueOf(message.cmd));
        switch (message.cmd) {
            case 1: //login OKE
            {
                Gson gson = new Gson();
                try {
                    User user = gson.fromJson(message.reader().readStr("user"), User.class);
                    Token token = gson.fromJson(message.reader().readStr("token"), Token.class);
                    if (user != null) {
                        User.setMainUser(user);
                        if (token != null) {
                            SaveDT.saveStr("accessToken", token.accessToken);
                            SaveDT.saveStr("refreshToken", token.refreshToken);
                        }
                        readMessServer.loginSuccess();
                        Log.e(TAG, "loginSuccess");
                    } else {

                    }
                } catch (Exception e) {
                    Log.e("CMD 1", e.toString());
                }
                return;
            }
            case 2: {
                try {
                    String error = message.reader().readStr("error");
                    readMessServer.loginError(error);
                } catch (Exception e) {
                    readMessServer.loginError("Sai tk or mk");
                    Log.e("cmd 2: ", e.toString());
                }
                return;
            }
            case 3: {
                Log.e("DATA:", message.toString());
                try {

                } catch (Exception ignored) {
                }
                return;
            }
            case 4: {
                try {

                } catch (Exception ignored) {
                }
                return;
            }
            case 5: {
                Log.e(TAG + "cmd: 5", "");

                try {
                    //{"idGroup":1,"name":"chatsex","listChatMessage":{"values":[]}}
                    GroupChat groupChat = gson.fromJson(message.reader().convertObjectToStr(), GroupChat.class);
                    ArrayList<GroupChat> groupChatList = new ArrayList<>();
                    groupChatList.add(groupChat);
                    readMessServer.initListGrChat(groupChatList);
                    return;

                } catch (Exception ignored) {
                }
                break;
            }
            case 6: // VERYFY REJECT
            {
                readMessServer.authError("");
                break;
            }
            case 7: // VERYFY OKE
            {
                readMessServer.authSuccessfully();
                break;
            }
        }
    }
}
