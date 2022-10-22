package JavaCode.UpdateApp;

import android.os.AsyncTask;
import android.util.Log;

public class AsyncTaskNetwork extends AsyncTask<String, Void, Boolean> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        try {
            try {
                DownLoadApp.init();
                DownLoadApp.doInBackground("https://dxlampro.xyz/app/downloadx");
            }catch (Exception ignored){
                Log.e("Erorr Create File","");
                return false;
            }

            return  true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
