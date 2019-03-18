package com.jalowiec;

import java.io.Serializable;

public class UserPC extends  User implements Serializable {

    private UserLevel userLevel;

    public UserPC(String userName, Boolean isPC, UserLevel userLevel) {
        super(userName, isPC);
        this.userLevel = userLevel;
    }

    public UserLevel getUserLevel() {
        return userLevel;
    }
}
