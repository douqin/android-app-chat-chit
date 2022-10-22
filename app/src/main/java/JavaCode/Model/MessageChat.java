package JavaCode.Model;

public class MessageChat {
    private MessageChat(User user, GroupChat groupChat, String message, int typeChat, String time, int status) {
        this.user = user;
        this.groupChat = groupChat;
        this.message = message;
        this.typeChat = typeChat;
        this.time = time;
        this.status = status;
    }
    public User user;
    public GroupChat groupChat;
    public String message;
    public int typeChat;
    public String time;
    public int status;
    public final static int TYPE_CHAT_IMG = 1;
    public final static int TYPE_CHAT_STR = 2;
    public final static int TYPE_CHAT_VIDEO = 3;
    public final static int TYPE_CHAT_HYPER_LINK = 4;
    public final static int TYPE_CHAT_GIF = 5;

    public static class Builder {
        private User user;
        private GroupChat groupChat;
        private String message;
        private int typeChat;
        private String time;
        private int status;

        public Builder setGroupChat(GroupChat groupChat) {
            this.groupChat = groupChat;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTypeChat(int typeChat) {
            this.typeChat = typeChat;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder() {
        }

        public MessageChat Build() {
            return new MessageChat(this.user, this.groupChat, this.message, this.typeChat, this.time, this.status);
        }
    }
}
