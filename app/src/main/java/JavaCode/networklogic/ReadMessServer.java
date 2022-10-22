package JavaCode.networklogic;

import android.util.Log;

import com.dxlampro.appchat.iHandlerApp;

import java.util.List;

import JavaCode.Model.GroupChat;
import JavaCode.Screen.Login.iResultLogin;
import JavaCode.Screen.Splash.iAuthResult;
import JavaCode.Screen.StartChat.iResultChat;

public class ReadMessServer {
    private static final String TAG = "ReadMessServer";
    private iResultLogin resultLogin;
    private iResultChat resultChat;
    private iAuthResult authResult;
    private iHandlerApp handlerApp;
    public ReadMessServer() {
        me = this;
    }

    private static ReadMessServer me;

    public static void setLogin(iResultLogin iResultLogin) {
        me.resultLogin = iResultLogin;
    }

    public static void setStartChat(iResultChat iResultLogin) {
        me.resultChat = iResultLogin;
    }

    public static void setAuth(iAuthResult iAuthResult) {
        me.authResult = iAuthResult;
    }

    public static void setHandler(iHandlerApp handlerApp) {
        me.handlerApp = handlerApp;
    }

    public void loginSuccess() {
        if (resultLogin != null)
            resultLogin.loginSuccess();
    }
    public void loginError(String msgError) {
        if (resultLogin != null)
            resultLogin.loginError(msgError);
    }
    public void initListGrChat(List<GroupChat> groupChats){
        if(resultChat != null && groupChats != null){
            resultChat.initListGroupChat(groupChats);
        }
        else {
            Log.e(TAG," null initListGrChat");
        }
    }
    public void authSuccessfully(){
        authResult.authSuccessfully();
    }

    public void authError(String msg){
        authResult.authError(msg);
    }

    public void onConnectionFail(){
        handlerApp.onConnectFail();
    }
    public void onDisconnected() {
        handlerApp.onDisconnectServer();
    }
    public void onConnectOK() {
        handlerApp.onConnectOK();
    }


//    public void addMessChat(Message msg) {
//       /*
//        * [
//        *     {
//        *         "cmd": 3
//        *     },
//        *     {
//        *         "messagechat": {
//        *             "message": "data",
//        *             "time": "2022-08-27T08:31:20.195Z",
//        *             "typeChat": 1,
//        *             "user": {
//        *                 "iduser": "2",
//        *                 "email": "",
//        *                 "name": "hau oc buoi",
//        *                 "birth": "2022-08-27T08:31:20.195Z",
//        *                 "address": "",
//        *                 "avatar": null
//        *             }
//        *         }
//        *     }
//        * ]
//         */
//       JSONObject ob = msg.reader().getObject("messagechat");
//       Gson gson = new Gson();
//       try {
//           MessageChat mess = gson.fromJson(ob.toString(),MessageChat.class);
//           AppCanvas.screenChat.mMessageChat.add(mess);
//       }
//       catch (Exception error){
//           Log.e("Exception ReadMessServer", "addMessChat: " + error.toString());
//       }
////       AppCanvas.screenChat.mMessageChat.add(new MessageChat(textChat,((iduser == User.gI().iduser) ? MessageApdater.ME_STR : MessageApdater.NOT_ME_STR)));
//
//    }
//    public void addImgChat(Message msg) throws JSONException{
////        /**
////         * [{"cmd":4},{"iduser":"1","chat":"ma str base 64"}]
////         */
////        String textChat = msg.reader().readStr("chat");
////        int iduser = msg.reader().readInt("iduser");
////        AppCanvas.screenChat.mMessageChat.add(new MessageChat(textChat,((iduser == User.gI().iduser) ? MessageApdater.ME_STR : MessageApdater.NOT_ME_STR)));
////        AppCanvas.screenChat.updateListChat();
//    }
//    public void addListChat(Message msg) throws JSONException {
//        //{"idgroup":1,"name":"chatsex"},{"idgroup":1,"name":"chatsex"}
//        Log.e("Tag:", String.valueOf(msg));
//        if(msg.reader().isArrayNull()){
//            AppCanvas.screenstartchat.addUserChat(new GroupChat(msg.reader().readInt("idgroup"), msg.reader().readStr("name")));
//            AppCanvas.screenstartchat.getListComplete();
//            return;
//        }
//        for(int i = 0; i < msg.reader().length(); i++) {
//            Message _msg = new Message((JSONObject) msg.reader().atElement(i));
//            AppCanvas.screenstartchat.addUserChat(new GroupChat(_msg.reader().readInt("idgroup"), _msg.reader().readStr("name")));
//        }
//        AppCanvas.screenstartchat.getListComplete();
//    }

}
//{"iduser":1,"email":"dagxuanlam@gmail.com","name":"Đặng Xuân Lâm","birthday":"2021-11-10T23:39:04.000Z","address":"thon 11","avatar":null}
