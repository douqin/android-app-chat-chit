package com.douqin.chatchitVN.data.apis;

import android.os.Build;

import com.douqin.chatchitVN.BuildConfig;
import com.douqin.chatchitVN.common.SaveDT;
import com.douqin.chatchitVN.data.repositories.login.dtos.Token;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class BaseConfigAPI {

    static String BASE_URL_SOCKET = "";
    public static String BASE_URL = "http://192.168.1.8:3000/";
    static final HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS);
    public static final OkHttpClient.Builder okBuild = new OkHttpClient.Builder()
            .readTimeout(15000, TimeUnit.SECONDS)
            .connectTimeout(15000, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLogging)
            .addInterceptor(chain -> {
                int verCode = BuildConfig.VERSION_CODE;
                String verName = BuildConfig.VERSION_NAME;
                String deviceInformation = "Linux;Android " + Build.VERSION.RELEASE + " (API level " + Build.VERSION.SDK_INT + ");" + Build.MANUFACTURER;
                String appInfo = "VERSION_APP " + verName + "+" + verCode;
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("sec-ch-uea-platform", "Android")
                        .header("User-Agent", deviceInformation + ";" + appInfo);
                String tokenStr = SaveDT.loadData("token");
                if (!tokenStr.isEmpty()) {
                    Token token = (new Gson()).fromJson(tokenStr, Token.class);
                    requestBuilder.header("token", "Bearer " + token.accessToken);
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });

    public static void setTokenInHeader(Token token) {
        okBuild.addInterceptor(chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("token", "Bearer " + token.accessToken);
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });
    }
}
