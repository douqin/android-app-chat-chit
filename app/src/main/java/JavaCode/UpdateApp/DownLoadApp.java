package JavaCode.UpdateApp;

import android.Manifest;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.dxlampro.appchat.Main;
import com.dxlampro.appchat.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import JavaCode.AMain.AppCanvas;

public class DownLoadApp extends Service {
     public static String pathFileApp = Environment.getExternalStorageDirectory().getPath() + File.separator + "AppNew" + File.separator + "APP.apk";
     public static String pathFolderApp = Environment.getExternalStorageDirectory().getPath() + File.separator + "AppNew";

     public int checkVersionInApp() throws PackageManager.NameNotFoundException {
         PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(this.getApplicationContext().getPackageName(), 0);
         int curVersionCode = packageInfo.versionCode;
         return curVersionCode;
     }
     public static void init() throws IOException {
         createFile();
     }
     public static void createFile() throws IOException {
         //create folder
         File directory1 = new File(JavaCode.UpdateApp.DownLoadApp.pathFolderApp);
         if (directory1.mkdir()) {
             //create file APK
             File directory = new File(JavaCode.UpdateApp.DownLoadApp.pathFileApp);
             if (directory.createNewFile()) {
                 Log.e("CreateFile", "Successfully");
             }
         }
     }

     public static void deleteFile() {
         File fdelete = new File(JavaCode.UpdateApp.DownLoadApp.pathFileApp);
         if (fdelete.exists()) {
             if (fdelete.delete()) {
                 Log.e("file Deleted ", "OKE");
             } else {
                 Log.e("file not Deleted ", "OKE");
             }
         }
     }

     @Override
     public void onCreate() {
         super.onCreate();
         Log.e("Service Update", "Active");
     }


     @Override
     public int onStartCommand(Intent intent, int flags, int startId) {
         InstallApp.run();
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
             sendNoti();
         }
         return START_NOT_STICKY;
     }

     public  Runnable InstallApp = () -> {
         try {
             DownLoadApp.init();
             doInBackground("https://dxlampro.xyz/app/downloadx");
             onPostExecute();
         } catch (IOException e) {
             e.printStackTrace();
         }
     };

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.e("Service Update","Destroy");
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
        protected synchronized static void doInBackground(String Url) {
            try {
                URL url = new URL(Url);
                URLConnection connection = url.openConnection();
                connection.connect();

                int fileLength = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(pathFileApp);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    total += count;
                  //  publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();
//                Main.gI().Install();
            } catch (Exception e) {
                Log.e("YourApp", "Well that didn't work out so well...");
                Log.e("YourApp", e.getMessage());
            }
            Log.e("YourApp", "Well that work out so well...");
        }
        // begin the installation by opening the resulting file
        protected  void onPostExecute() {
            int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.REQUEST_INSTALL_PACKAGES);
            if(permissionCheck == -1){
//                Main.gI().Install();
            }
            if(permissionCheck == 1 ) {
//                Main.gI().Install();
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        private void sendNoti() {
            String path = "android.resource://" + getPackageName() + "/" + R.raw.sound_notification;
            Notification notification =
                    new Notification.Builder(this, Main.chanelID)
                            .setContentText("Updating App...")
                            .setSmallIcon(R.drawable.ic_logo_noti)
                            .setSound(Uri.parse(path))
                            .build();
            startForeground(1, notification);
        }
    }
