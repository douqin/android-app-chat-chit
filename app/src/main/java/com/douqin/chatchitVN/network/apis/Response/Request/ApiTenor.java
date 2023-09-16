package com.douqin.chatchitVN.network.apis.Response.Request;

import com.douqin.chatchitVN.network.apis.BaseConfigAPI;
import com.douqin.chatchitVN.network.apis.RemoteData.TenorRemoteData;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiTenor {
    static String keyAPI = "AIzaSyCOMj_1PaDtM9689q0jbP2Vk2M7w8ztUrE";
    static int limit = 17;
    static ApiTenor apiTenor = new Retrofit.Builder()
            .baseUrl("https://tenor.googleapis.com/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiTenor.class);

    @GET("v2/search")
    Observable<TenorRemoteData> getGiftFromKeyword(@Query("q") String keyword, @Query("key") String keyAPI, @Query("limit") int limit);
}
