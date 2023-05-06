package JavaCode.Api;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface ApiLogin {
    ApiLogin loginService = new Retrofit.Builder()
            .baseUrl(BaseConfigRetrofit.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(BaseConfigRetrofit.okBuild.build())
            .build()
            .create(ApiLogin.class);
}
