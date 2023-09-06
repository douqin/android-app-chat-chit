package com.douqin.chatchitVN.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceInfoUtils {
    public static String IMEI = "";

    @SuppressLint("HardwareIds")
    public static String initIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                return telephonyManager.getImei();
            } else {
                return telephonyManager.getDeviceId();
            }
        }
        return null;
    }
}
