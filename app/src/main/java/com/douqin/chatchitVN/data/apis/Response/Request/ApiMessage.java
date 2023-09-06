package com.douqin.chatchitVN.data.apis.Response.Request;

import com.douqin.chatchitVN.data.apis.BaseConfigAPI;
import com.douqin.chatchitVN.data.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.data.apis.dtos.MessageDTO;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMessage {
    ApiMessage messgaeService = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiMessage.class);

    @GET("messagge/{idgroup}")
    Observable<ResponseAPI<List<MessageDTO>>> getMessageFromGroupID(@Path("idgroup") int idgroup, @Query("time") String time);
}
