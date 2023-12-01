package com.douqin.chatchitVN.network.apis.Request;

import com.douqin.chatchitVN.network.apis.BaseConfigAPI;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface ApiRelationship {
    ApiRelationship apiRelationship = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL + "relationship/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiRelationship.class);
}
