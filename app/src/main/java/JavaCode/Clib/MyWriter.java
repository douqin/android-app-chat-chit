package JavaCode.Clib;

import org.json.JSONException;
import org.json.JSONObject;

public class MyWriter {
    private JSONObject object = new JSONObject();

    public MyWriter() {
    }

    public void writeUTF(String key, String value) throws JSONException {
        object.put(key, value);
    }

    public void writeByte(String key, byte value) throws JSONException {
        object.put(key, value);
    }

    public void writeInt(String key, int value) throws JSONException {
        object.put(key, value);
    }

    public JSONObject getData() {
        return object;
    }

}
