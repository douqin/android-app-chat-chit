package com.douqin.chatchitVN.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SaveDT {
    private static final String TAG = "SaveDT";
    private static Context context;

    public static void initContext(Context c) {
        context = c;
    }

    @SuppressLint({"WorldWriteableFiles"})
    public static void saveRMS(String name, byte[] data) {
        try {
            FileOutputStream fos = context.openFileOutput(name, 0);
            fos.write(data);
            fos.close();
        } catch (Exception ignored) {
        }
    }

    public static byte[] loadRMS(String name) {
        try {
            FileInputStream fis = SaveDT.context.openFileInput(name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            while (true) {
                int bytesRead = fis.read(b);
                if (bytesRead == -1) {
                    break;
                }
                bos.write(b, 0, bytesRead);
            }
            byte[] data = bos.toByteArray();
            fis.close();
            return data;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }

    public static void saveStr(String nameFile, String data) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeUTF(data);
            //   dos.writeByte(ListChar_Screen.IndexCharSelected);
            //  dos.writeByte((byte) GameCanvas.IndexServer);
            saveRMS(nameFile, bos.toByteArray());
            dos.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    public static String loadData(String nameFile) {
        byte[] bData = loadRMS(nameFile);
        String data = "";
        if (bData != null) {
            try {
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bData));
                data = dis.readUTF();
                dis.close();
            } catch (Exception e) {
                Log.e("Error load data", e.toString());
            }
        }
        return data;
    }
}
