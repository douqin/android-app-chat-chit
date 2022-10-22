package JavaCode.Model;

public class User {
    private static User me;
    public String avatar;
    public String email;
    public int iduser;
    public String name;
    public String address;
    public String birth;

    public User(String avatar, String email, int iduser, String name, String address, String birth) {
        this.avatar = avatar;
        this.email = email;
        this.iduser = iduser;
        this.name = name;
        this.address = address;
        this.birth = birth;
    }
    public static void setMainUser(User user){
        me = user;
    }
    public User getMainUser(){
        return me;
    }
    public static class Builder {
        private String avatar;
        private String email;
        private int iduser;
        private String name;
        private String address;
        private String birth;

        private Builder() {
        }
        public Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setIduser(int iduser) {
            this.iduser = iduser;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setBirth(String birth) {
            this.birth = birth;
            return this;
        }


    }
}
