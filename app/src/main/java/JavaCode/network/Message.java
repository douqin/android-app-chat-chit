package JavaCode.network;

import org.json.JSONObject;

import JavaCode.Clib.MyReader;
import JavaCode.Clib.MyWriter;


public class Message {
    public String type;
    public int cmd;
    private MyWriter dos;
    private MyReader dis;

    public Message() {
        this.dos = new MyWriter();
    }

    public Message(int command, JSONObject data) {
        this.cmd = command;
        this.dis = new MyReader(data);
    }

    public Message(JSONObject data) {
        this.cmd = 0;
        this.dis = new MyReader(data);
    }

    public JSONObject getData() {
        return this.dos.getData();
    }

    public MyReader reader() {
        return this.dis;
    }

    public MyWriter writer() {
        return this.dos;
    }
}
