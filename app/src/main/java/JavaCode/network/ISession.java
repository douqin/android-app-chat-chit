package JavaCode.network;

public interface ISession {
    void close();

    void connect(String str, String str2);

    boolean isConnected();

    void sendMessage(Message message);

    void setHandler(IMessageHandler iMessageHandler);
}
