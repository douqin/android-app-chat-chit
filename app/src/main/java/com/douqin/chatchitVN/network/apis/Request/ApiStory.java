package com.douqin.chatchitVN.network.apis.Request;

import com.douqin.chatchitVN.network.apis.BaseConfigAPI;
import com.douqin.chatchitVN.network.apis.RemoteData.StoryRemoteData;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiStory {
    ApiStory storyService = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiStory.class);

    @GET("story")
    Observable<ResponseAPI<List<StoryRemoteData>>> getAllStoryFromFriends();


    @POST("story/upload")
    @Multipart()
    Observable<ResponseAPI<Object>> uploadStory(@Part MultipartBody.Part file);
}
