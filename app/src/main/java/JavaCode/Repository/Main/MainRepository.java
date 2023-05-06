package JavaCode.Repository.Main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dxlampro.appchat.iListeningStatusIO;

import JavaCode.Clib.StatusIO;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainRepository implements iListeningStatusIO {

    public MainRepository(Application application) {

    }

    public LiveData<String> getStatusApp() {
        return statusCurrentApp;
    }

    private MutableLiveData<String> statusCurrentApp = new MutableLiveData<>("");

    @Override
    public void onChangeStatus(StatusIO statusIO) {
        switch (statusIO) {
            case CONNECTED:
                statusCurrentApp.postValue("Đã kết nối");
                Observable.create(emitter ->
                                {
                                    Thread.sleep(5000);
                                    emitter.onComplete();
                                }
                        ).subscribeOn(Schedulers.newThread())
                        .observeOn(Schedulers.newThread())
                        .subscribe(onNext -> {
                            statusCurrentApp.postValue("");
                        }, onError -> {
                            statusCurrentApp.postValue("");
                        });
                break;
            case DISCONNECT:
                statusCurrentApp.postValue("Mất kết nối");
                break;
            case CONNECTING:
                statusCurrentApp.postValue("Đang kết nối");
                break;
        }
    }

    public void listeningStatusInternet(Boolean isInternet) {
//        if(isInternet){
//            statusCurrentApp.postValue("Đang kết nối tới Server");
//            Session_ME.gI().setHandler(GlobalMessageHandler.gI());
//            Session_ME.gI().connect("http://192.168.1.3", "3000");
        //                socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
        //        //    @Override
        //        //    public void call(Object... args) {
        //        //        options.auth.put("authorization", "bearer 1234");
        //        //        socket.connect();
        //        //    }
        //        //});
        //
        //        //socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
        //        //    @Override
        //        //    public void call(Object... args) {
        //        //        System.out.println(socket.connected()); // true
        //        //    }
        //        //});
        //
        //        //socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
        //        //    @Override
        //        //    public void call(Object... args) {
        //        //        System.out.println(socket.connected()); // false
        //        //    }
        //        //});
//        } else {
//            statusCurrentApp.postValue("Không có kết nối Internet");
//        }


    }


}
