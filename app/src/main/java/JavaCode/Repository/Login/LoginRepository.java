package JavaCode.Repository.Login;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import JavaCode.Api.ApiAuth;
import JavaCode.Clib.SaveDT;
import JavaCode.Repository.Login.dtos.LoginSuccessfully;
import JavaCode.Repository.User.UserManager;
import JavaCode.Screen.Login.StateLogin;
import JavaCode.Screen.Login.cStateLogin;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginRepository implements iLoginRepository {
    private MutableLiveData<cStateLogin> stateLogin = new MutableLiveData<>(new cStateLogin(StateLogin.NO_STATE, ""));

    public LoginRepository() {

    }

    @Override
    public void login(String phone, String password) {
        RequestBody phoneRequestBody = RequestBody.create(phone, MediaType.parse("text/plain"));
        RequestBody passwordRequestBody = RequestBody.create(password, MediaType.parse("text/plain"));
        ApiAuth.loginService.login(phoneRequestBody, passwordRequestBody)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<LoginSuccessfully>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LoginSuccessfully s) {
                        UserManager.gI().setMySelf(s.user);
                        Gson gson = new Gson();
                        SaveDT.saveStr("token", gson.toJson(s.token));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
//                        stateLogin.postValue(new cStateLogin(StateLogin.ERROR, e.getMessage()));
                        Log.w(LoginRepository.class.getSimpleName(), e.getMessage());
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
