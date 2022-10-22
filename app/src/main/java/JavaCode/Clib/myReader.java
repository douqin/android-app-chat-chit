package JavaCode.Clib;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class myReader {
    JSONObject jsonObject;
    public myReader(JSONObject data) {
        jsonObject = data;
    }
    public int readInt(String key) throws JSONException {
        return jsonObject.getInt(key);
    }
    public String readStr(String key) throws JSONException {
        return jsonObject.getString(key);
    }
    public String convertObjectToStr(){
        return jsonObject.toString();
    }

    public JSONObject getObject(String key){
        try {
            return this.jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            Log.e("getObject  myReader", "getObject");
            return  null;

        }
    }
}
//{"iduser":1,"email":"dagxuanlam@gmail.com","name":"Đặng Xuân Lâm","birthday":"2021-11-10T23:39:04.000Z","address":"thon 11","avatar":null}