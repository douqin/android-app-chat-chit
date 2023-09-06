package com.douqin.chatchitVN.data.apis.Response.Request;

import com.douqin.chatchitVN.data.apis.BaseConfigAPI;
import com.douqin.chatchitVN.data.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.data.repositories.login.dtos.LoginDTO;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiAuth {
    ApiAuth loginService = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL + "auth/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiAuth.class);

    @POST("login")
    @Multipart()
    Observable<ResponseAPI<LoginDTO>> login(@Part("phone") RequestBody phone,
                                            @Part("password") RequestBody password);

    @POST("refreshtoken")
    @Multipart()
    Observable<LoginDTO> refreshToken(@Part("phone") RequestBody phone,
                                      @Part("password") RequestBody password);

    @POST("signup")
    @Multipart()
    Observable<LoginDTO> signup(@Part("phone") RequestBody phone,
                                @Part("password") RequestBody password);
}
