package com.douqin.chatchitVN.network.apis.Request;

import com.douqin.chatchitVN.network.apis.BaseConfigAPI;
import com.douqin.chatchitVN.network.apis.RemoteData.GroupChatRemoteData;
import com.douqin.chatchitVN.network.apis.RemoteData.MemberRemoteData;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiGroup {
    ApiGroup groupService = new Retrofit.Builder()
            .baseUrl(BaseConfigAPI.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigAPI.okBuild.build())
            .build()
            .create(ApiGroup.class);


    @GET("/group/{id}/getallmembers")
    Observable<ResponseAPI<List<MemberRemoteData>>> getAllMemberFromServer(@Path("id") int idgroup);

    @GET("group")
    Observable<ResponseAPI<List<GroupChatRemoteData>>> syncWithServer(
            @Query("time") String time
    );

    @GET("group/")
    Observable<ResponseAPI<List<GroupChatRemoteData>>> getAllGroup();

    @GET("group/{id}")
    Observable<GroupChatRemoteData> getGroup(@Path("id") int group);

    @POST("group/create")
    Observable<String> createGroup();

    @PATCH("group/{id}/avatar")
    Observable<List<GroupChatRemoteData>> changeAvatar(@Part("id") int group);

    @POST("group/{id}/lastview")
    Observable<List<Integer>> getLastView(@Part("id") int group);

    //[PATCH] /:id/rename đổi tên group
    @POST("group/{id}/rename")
    Observable<String> renameGroup(@Part("id") int group);

    //[GET] /:id/getallmembers lấy hết thoong tin user có trong group có id
    @POST("group/{id}/getallmembers")
    Observable<String> getInforAllMember(@Part("id") int group);

    //[POST] /:id/invitemembers mời thành viên tham gia
    @POST("group/{id}/invitemembers")
    Observable<String> inviteMemeber(@Part("id") int group);

    //[POST] /:id/members/leave rời nhóm
    @POST("group/{id}/members/leave")
    Observable<String> leaveGroup();
}
