package JavaCode.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = false)
    public int iduser;

    public String avatar;
    public String phone;
    public String name;
    public String address;
    public String birthday;
    @ColumnInfo(defaultValue = "0")
    public int isMe = 0;

    public int gender;

    public User(int iduser, String phone, String name, String avatar, String birthday, int gender, String address) {
        this.avatar = avatar;
        this.phone = phone;
        this.iduser = iduser;
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
    }
}
