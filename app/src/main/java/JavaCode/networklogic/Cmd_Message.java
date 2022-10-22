package JavaCode.networklogic;

import org.json.JSONException;

import JavaCode.network.ISession;
import JavaCode.network.Message;
import JavaCode.network.Session_ME;

public class Cmd_Message {
    public ISession session = Session_ME.gI();
    protected Message m;

    //endregion
    public void init() throws JSONException {
        this.m = new Message();
    }
    public void send()
    {
        this.session.sendMessage(this.m);
    }
}
