package JavaCode.Api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

class BaseConfigRetrofit {
    static String BASE_URL = "http://192.168.1.3";
    static final HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
    static final OkHttpClient.Builder okBuild = new OkHttpClient.Builder()
            .readTimeout(15000, TimeUnit.SECONDS)
            .connectTimeout(15000, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(httpLogging);
}
