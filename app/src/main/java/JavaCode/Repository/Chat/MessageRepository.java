package JavaCode.Repository.Chat;

import JavaCode.LocalData.Room.Dao.MessageDao;

public class MessageRepository {

    public MessageRepository(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.registerEmitter();
    }

    private MessageDao messageDao;

    private void registerEmitter() {
        // TODO:
    }

}
