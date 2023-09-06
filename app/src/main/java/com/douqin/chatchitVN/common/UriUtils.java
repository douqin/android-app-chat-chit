package com.douqin.chatchitVN.common;

import android.app.Application;
import android.net.Uri;

public class UriUtils {
    public static Uri getUriFromRes(Application application, int pathRes) {
        return Uri.parse("android.resource://"
                + application.getApplicationContext().getPackageName() + "/" + pathRes);
    }
}
