package JavaCode.Api;

import android.os.Build;

import com.dxlampro.appchat.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class BaseConfigAPI {
    static String BASE_URL = "http://192.168.1.6:3000";
    static final HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    static final OkHttpClient.Builder okBuild = new OkHttpClient.Builder()
            .readTimeout(15000, TimeUnit.SECONDS)
            .connectTimeout(15000, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLogging)
            .addInterceptor(chain -> {
                int verCode = BuildConfig.VERSION_CODE;
                String verName = BuildConfig.VERSION_NAME;
                String devideInformation = new StringBuilder().append("Linux;Android ").append(Build.VERSION.RELEASE).append(" (API level ").append(Build.VERSION.SDK_INT).append(");").append(Build.MANUFACTURER).toString();
                String appInfor = new StringBuilder().append("VERSION_APP ").append(verName).append("+").append(verCode).toString();
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .header("sec-ch-ua-platform", "Android")
                        .header("User-Agent", devideInformation + ";" + appInfor);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
}
