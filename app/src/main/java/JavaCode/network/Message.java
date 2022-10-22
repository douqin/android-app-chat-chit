package JavaCode.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import JavaCode.Clib.myReader;
import JavaCode.Clib.myWriter;


public class Message {
    public String type;
    public int cmd;
    private myWriter dos;
    private myReader dis;

    public Message() throws JSONException {
        this.dos = new myWriter();
    }
    public Message(int command, JSONObject data) {
        this.cmd = command;
        this.dis = new myReader(data);
    }
    public Message( JSONObject data) {
        this.cmd = 0;
        this.dis = new myReader(data);
    }
    public JSONObject getData()
    {
        return this.dos.getData();
    }
    public myReader reader()
    {
        return this.dis;
    }
    public myWriter writer()
    {
        return this.dos;
    }
}
