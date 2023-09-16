package com.douqin.chatchitVN.data.repositories.login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.douqin.chatchitVN.data.repositories.login.dtos.LoginDTO;
import com.douqin.chatchitVN.data.repositories.user.MeManager;
import com.douqin.chatchitVN.network.apis.BaseConfigAPI;
import com.douqin.chatchitVN.network.apis.Response.Request.ApiAuth;
import com.douqin.chatchitVN.network.apis.Response.ResponseAPI;
import com.douqin.chatchitVN.ui.login.StateLogin;
import com.douqin.chatchitVN.ui.login.cStateLogin;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginRepository implements iLoginRepository {
    private final MutableLiveData<cStateLogin> stateLogin = new MutableLiveData<>(new cStateLogin(StateLogin.NO_STATE, ""));

    public LoginRepository() {

    }

    @Override
    public void login(String phone, String password) {
        RequestBody phoneRequestBody = RequestBody.create(phone, MediaType.parse("text/plain"));
        RequestBody passwordRequestBody = RequestBody.create(password, MediaType.parse("text/plain"));
        ApiAuth.loginService.login(phoneRequestBody, passwordRequestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseAPI<LoginDTO>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseAPI<LoginDTO> s) {
                        MeManager.gI().setMySelf(s.data.user);
                        MeManager.gI().setToken(s.data.token);
                        BaseConfigAPI.setTokenInHeader(s.data.token);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof HttpException) {
                            ResponseBody body = Objects.requireNonNull(((HttpException) e).response()).errorBody();
                            try {
                                ResponseAPI<Object> responseAPIError = (new Gson()).fromJson(body.string(), new TypeToken<ResponseAPI<Object>>() {
                                }.getType());
                                stateLogin.postValue(new cStateLogin(StateLogin.ERROR, responseAPIError.message));
                                return;
                            } catch (IOException ex) {
                                Log.w(LoginRepository.class.getSimpleName(), ex.getMessage());
                            }
                        }
                        stateLogin.postValue(new cStateLogin(StateLogin.ERROR, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        stateLogin.setValue(new cStateLogin(StateLogin.SUCCESSFULLY, ""));
                    }
                });
    }

    @Override
    public void signUp() {
    }

    @Override
    public LiveData<cStateLogin> getStateLogin() {
        return stateLogin;
    }

}
