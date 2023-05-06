package JavaCode.network;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import JavaCode.Clib.SaveDT;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.socket.client.IO;
import io.socket.client.Socket;

public class Session_ME implements ISession {
    protected static Session_ME instance = new Session_ME();
    public static IMessageHandler messageHandler;

    public Message a;
    private Socket sc;

    public void connect(String host, String port) {
        this.sc = null;
        Disposable disposable = Observable.fromCallable(() -> {
                    String url = host + ":" + port;
                    Gson gson = new Gson();
                    String json = gson.toJson(SaveDT.loadData("token"));
                    Map<String, String> token = new HashMap<>();
                    token.put("token", json);
                    IO.Options options = IO.Options.builder()
                            .setAuth(token)
                            .build();
                    Socket socket = IO.socket(URI.create(url), options).connect();
                    return socket;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socket -> {
                    Session_ME.this.sc = socket;
                    startListeningOn();
//                        Session_ME.messageHandler.onConnectOK();
                }, error -> {
//                        Session_ME.messageHandler.onConnectionFail();
                });

    }

    public boolean isConnected() {
        return this.sc.connected();
    }

    public static Session_ME gI() {
        return instance;
    }


    public void startListeningOn() {
        Session_ME.gI().sc.on("typing", args -> {
            Disposable disposable = Flowable.create(emitter -> {
                        if (Session_ME.this.sc.connected() && args[0] != null) {
                            Log.e("co nguoi typing tin den:", args[0].toString());
                        }
                    }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.single())
                    .observeOn(Schedulers.io())
                    .subscribe(
                            message -> {

                            }, throwable -> {

                            }
                    );
        });
        Session_ME.gI().sc.on("serverguitinnhan", args -> {
            Disposable disposable = Flowable.create(emitter -> {
                        if (Session_ME.this.sc.connected() && args[0] != null) {
                            Log.e("co tin den:", args[0].toString());
                            Log.e("args", args[0].toString());
                            //  if(args.length > 0) {
                            //     JSONObject cmd = (JSONObject) args[0];
                            try {
                                JSONObject jsonObject = null;
                                JSONArray arrayjson = (JSONArray) args[0];
                                int cmd = arrayjson.getJSONObject(0).getInt("cmd");
                                try {
                                    jsonObject = arrayjson.getJSONObject(1);
                                } catch (Exception e) {
                                    Log.e(this.getClass().getSimpleName(), "Error handler data from server");
                                }
                                emitter.onNext(new Message(cmd, jsonObject));
                                emitter.onComplete();
                            } catch (Exception e) {
                                emitter.onError(new Throwable(e.toString()));
                                e.printStackTrace();
                            }
                        }
                    }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.single())
                    .observeOn(Schedulers.io())
                    .subscribe(
                            message -> {
                                messageHandler.onMessage((Message) message);
                            }, throwable -> {
                                Log.e(this.getClass().getSimpleName(), throwable.getMessage());
                            }
                    );
        });

        //socket.emit("hello", "world", new AckWithTimeout(5000) {
        //    @Override
        //    public void onTimeout() {
        //        // ...
        //    }
        //
        //    @Override
        //    public void onSuccess(Object... args) {
        //        // ...
        //    }
        //});
    }

    private synchronized void doSendMessage(Message m) {
        JSONObject data = m.getData();
        System.out.print(data);
        try {
            this.sc.emit(m.type, data);
            Log.e("data", data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addListener(List<Listener> listeners) {
        for (Listener listener : listeners) {
            this.sc.on(listener.event, listener.fn);
        }
    }
}
