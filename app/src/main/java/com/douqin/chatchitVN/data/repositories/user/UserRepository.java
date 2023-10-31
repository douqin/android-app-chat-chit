package com.douqin.chatchitVN.data.repositories.user;

import com.douqin.chatchitVN.data.local.room.dao.UserDao;

public class UserRepository {
    private UserDao userDao;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    void getAllFriends() {

    }
}
