package JavaCode.Clib;//package app.dxlampro.xyz.Clib;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.nio.charset.StandardCharsets;
//
//import io.socket.client.IO;
//import io.socket.client.Socket;
//
//public class mSocket {
//    private Socket mSocket ;
//    private static mSocket instance;
//    private mSocket() {
//        try {
//            mSocket = IO.socket("http://192.168.100.10:3000");
//            mSocket.connect();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public static mSocket gI(){
//        if(instance != null)
//        {
//            return instance;
//        }
//        return instance = new mSocket();
//    }
//    public void chat(String textChat){
//        mSocket.emit("chat",textChat);
//    }
//    public void send(String event,Object a)
//    {
//        mSocket.emit(event,a);
//    }
//    public void senD() throws JSONException {
//        byte[] buffer = "LAM DZ PRO MAX".getBytes();
//        JSONObject object = new JSONObject();
//        object.put("test", "42");
//
//        mSocket.emit("hello", 1, "2", buffer, object);
//    }
//    public boolean checkConnect(){
//        return mSocket.connected();
//    }
//    public void connect(){
//        mSocket.connect();
//    }
//
//}
