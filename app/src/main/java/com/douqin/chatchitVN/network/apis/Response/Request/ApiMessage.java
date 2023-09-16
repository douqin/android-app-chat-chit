package com.douqin.chatchitVN.network.apis.Response.Request;

import com.douqin.chatchitVN.network.apis.BaseConfigAPI;
import com.douqin.chatchitVN.network.apis.RemoteData.MessageRemoteData;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiMessage {
    ApiMessage messageService = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiMessage.class);

    @GET("message/{idgroup}")
    Observable<ResponseAPI<List<MessageRemoteData>>> getMessageFromGroupID(@Path("idgroup") int idgroup, @Query("time") String time);

    @POST("message/{idgroup}/file")
    @Multipart()
    Observable<ResponseAPI<List<MessageRemoteData>>> sendFileMessage(@Path("idgroup") int idgroup, @Part MultipartBody.Part file);

    @POST("message/{idgroup}/text")
    @Multipart()
    Observable<ResponseAPI<MessageRemoteData>> sendTextMessage(@Path("idgroup") int idgroup, @Part("message") RequestBody message);
}
