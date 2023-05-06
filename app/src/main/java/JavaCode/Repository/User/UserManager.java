package JavaCode.Repository.User;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import JavaCode.LocalData.Room.Dao.MySelfDao;
import JavaCode.LocalData.Room.Database.AppDatabase;
import JavaCode.Model.User;

public class UserManager {
    private UserManager() {

    }
    public LiveData<User> mySelf;
    private static UserManager instance;

    private MySelfDao mySelfDao;

    public void initializeDAO(Application context) throws Exception {
        mySelfDao = AppDatabase.gI(context).mySelfDao();
        if(mySelfDao.getCount() != 1){
            throw new Exception("Không tìm thấy dữ liệu bản thân");
        }
        else {
            mySelf = mySelfDao.getMe();
        }
    }

    public static synchronized UserManager gI() {
        if (instance == null) {
            return instance = new UserManager();
        }
        return instance;
    }

    public void setMySelf(User user) {
        user.isMe = 1;
        this.mySelfDao.setMe(user);
    }
}
