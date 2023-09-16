package com.douqin.chatchitVN.data.models.UI;

import java.util.Date;

public class Member {

    public int id;

    public int idGroup;


    public int idUser;


    public Date lastView;


    public int position;


    public int status;

    public Date timeJoin;

    // Constructor
    public Member(int id, int idGroup, int idUser, Date lastView, int position, int status, Date timeJoin) {
        this.id = id;
        this.idGroup = idGroup;
        this.idUser = idUser;
        this.lastView = lastView;
        this.position = position;
        this.status = status;
        this.timeJoin = timeJoin;
    }

}
