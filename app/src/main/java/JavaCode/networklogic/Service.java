package JavaCode.networklogic;

import android.util.Log;

import org.json.JSONException;

public class Service extends Cmd_Message {
    private static Service instance;

    public static Service gI() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
    public void Login(String email, String pass){
        try{
            this.init();
            this.m.type ="login";
            this.m.writer().writeUTF("email",email);
            this.m.writer().writeUTF("pass",pass);
        }
        catch (Exception e){
            return;
        }
        super.send();
    }
    public void getListGrChat(){
        try {
            this.init();
            this.m.type= "getlistgroup";
        }
        catch (Exception e) {
            return;
        }
        super.send();
    }
    public void chatRoom(String text,int typeChat, int idGroup){
        try{
            this.init();
        this.m.type = "chatroom";
        this.m.writer().writeInt("idGroup", idGroup);
        this.m.writer().writeInt("typechat",typeChat);
        this.m.writer().writeUTF("chat",text);
        }
        catch (Exception e){
        }
        super.send();
    }
    public void auth() {
        try {
            this.init();
            this.m.type = "auth";
            super.send();
        }
        catch (Exception e){
            Log.e("auth Service", e.toString());
        }
    }
    public void getListChat(int idRoom)  {
        try{
            super.init();
            this.m.type = "getlistchat";
            this.m.writer().writeUTF("idroom", String.valueOf(idRoom));
            super.send();
        }
        catch (Exception exception){
            Log.e("Service", "getListChat");
        }
    }
    public void createGroupChat(String nameGr, String base64LogoGr){
        try{
            super.init();
            this.m.type = "creategroupchat";
            this.m.writer().writeUTF("namegr",nameGr);
            this.m.writer().writeUTF("logogr",base64LogoGr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
