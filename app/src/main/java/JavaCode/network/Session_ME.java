package JavaCode.network;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import JavaCode.Clib.SaveDT;
import JavaCode.Clib.mSystem;
import JavaCode.Clib.mVector;
import JavaCode.Model.dto.Token;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Session_ME implements ISession, Runnable {
    private static String TAG = "Session_ME";
    public static int countRead = 0;
    protected static Session_ME instance = new Session_ME();
    public static boolean isCancel;
    public static IMessageHandler messageHandler;
    public static mVector recieveMsg = new mVector();
    public Thread collectorThread;
    public boolean connected;
    public boolean connecting;
    public Message a;
    int countMsg = 0;
    private byte curR;
    private byte curW;
    //    public DataInputStream dis;
//    private DataOutputStream dos;
    boolean getKeyComplete;
    public Thread initThread;
    public byte[] key = null;
    public int recvByteCount;
    public Socket sc;
    public int sendByteCount;
    private final Sender sender = new Sender();
    //    public String strRecvByteCount = BuildConfig.FLAVOR;
    long timeConnected;

    @Override
    public void close() {

    }

    @Override
    public void connect(String host, String port) {
        if (!this.connected && !this.connecting) {
            this.sender.removeAllMessage();
            this.sc = null;
            this.initThread = new Thread(new NetworkInit(host, port));
            this.initThread.start();
        }
    }

    @Override
    public boolean isConnected() {
        return this.connected;
    }

    @Override
    public void sendMessage(Message message) {
        this.sender.AddMessage(message);
    }

    @Override
    public void setHandler(IMessageHandler iMessageHandler) {
        messageHandler = iMessageHandler;
    }

    public static Session_ME gI() {
        return instance;
    }

    @Override
    public void run() {

    }

    class NetworkInit implements Runnable {
        private final String host;
        private final String port;

        NetworkInit(String host2, String port2) {
            this.host = host2;
            this.port = port2;
        }

        public void run() {
            Session_ME.isCancel = false;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                    }
                    if (Session_ME.this.connecting) {
                        Session_ME.isCancel = true;
                        Session_ME.this.connecting = false;
                        Session_ME.this.connected = false;
                        Session_ME.messageHandler.onConnectionFail();
                    }
                }
            }).start();
            Session_ME.this.connecting = true;
            Thread.currentThread().setPriority(1);
            Session_ME.this.connected = true;
            try {
                doConnect2(this.host, this.port);
                Session_ME.messageHandler.onConnectOK();
            } catch (Exception e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e2) {
                }
                if (!Session_ME.isCancel && Session_ME.messageHandler != null) {
                    Session_ME.this.close();
                    Session_ME.messageHandler.onConnectionFail();
                }
            }
        }

        public void doConnect(String host2, String port) throws Exception {
            String url = host2 + ":" + port;
            Session_ME.this.sc = IO.socket(host2);
            Session_ME.this.sc.connect();
            Log.e("STATUS CONNECT:", "DANG KN");
            Session_ME.this.timeConnected = mSystem.currentTimeMillis();
            Session_ME.this.connecting = false;
            waitconnect();
        }

        public void doConnect2(String host2, String port) throws Exception {
            String url = host2 + ":" + port;
            String accessToken = SaveDT.loadData("accessToken");
            String refreshToken = SaveDT.loadData("refreshToken");
            if (accessToken != null && refreshToken != null) {
                Token _token = new Token(accessToken, refreshToken);
                Gson gson = new Gson();
                String json = gson.toJson(_token);
                Map<String, String> token = new HashMap<>();
                token.put("accessToken", accessToken);
                token.put("refreshToken", refreshToken);
                IO.Options options = IO.Options.builder()
                        .setAuth(token)
                        .build();
                Session_ME.this.sc = IO.socket(URI.create(url), options);
            } else {
                Session_ME.this.sc = IO.socket(url);
            }
            try {
                Session_ME.this.sc.connect();
            } catch (Exception e) {
                Log.e(TAG, "Error connect server");
            }

            Log.e("STATUS CONNECT:", "DANG KN");
            Session_ME.this.timeConnected = mSystem.currentTimeMillis();
            Session_ME.this.connecting = false;
            waitconnect();
        }

    }

    public void setStart() {
        new Thread(this.sender).start();
        this.collectorThread = new Thread(new MessageCollector());
        this.collectorThread.start();
    }

    /**
     * Receive mess
     */
    public class MessageCollector implements Runnable {
        MessageCollector() {
        }

        public void run() {
            Session_ME.gI().sc.on("serverguitinnhan", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
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
                                Log.e(TAG,"Error handler data from server");
                            }
                            messageHandler.onMessage(new Message(cmd,jsonObject));
                            Log.e(TAG,"Test");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!Session_ME.this.sc.connected() && Session_ME.this.sc != null) {
                    if (Session_ME.messageHandler != null) {
                        if (mSystem.currentTimeMillis() - Session_ME.this.timeConnected > 500) {
                            Session_ME.messageHandler.onDisconnected();
                        } else {
                            Session_ME.messageHandler.onConnectionFail();
                        }
                    }
                    Session_ME.this.cleanNetwork();
                    break;
                }
            }
        }

    }

    public void waitconnect() {
        boolean flag = true;
        while (flag) {
            if (mSystem.currentTimeMillis() - timeConnected <= 30000 && (mSystem.currentTimeMillis() / 1000) % 3 == 0) {
                if (sc.connected()) {
                    setStart();
                    flag = false;
                }
            }
            if (mSystem.currentTimeMillis() - timeConnected >= 30000) {
                break;
            }
        }
        if (!flag) {
            Log.e("STATUS CONNECT:", " EMIT AND ON TURN ON");
        }
    }

    private void cleanNetwork() {
        Session_ME.this.sc.close();
    }

    /**
     * Sender Message
     */

    class Sender implements Runnable {
        public final Vector sendingMessage = new Vector();

        public Sender() {
        }

        public void AddMessage(Message message) {
            this.sendingMessage.addElement(message);
        }

        public void removeAllMessage() {
            if (this.sendingMessage != null) {
                this.sendingMessage.removeAllElements();
            }
        }

        public void run() {
            while (Session_ME.this.connected) {
                // if (Session_ME.this.getKeyComplete) {
                while (this.sendingMessage.size() > 0) {
                    Session_ME.this.doSendMessage((Message) this.sendingMessage.elementAt(0));
                    this.sendingMessage.removeElementAt(0);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //    }
                }
            }
        }


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
}