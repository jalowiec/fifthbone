package com.jalowiec;

public class UserPC extends  User {

    private UserLevel userLevel;

    public UserPC(String userName, Boolean isPC, UserLevel userLevel) {
        super(userName, isPC);
        this.userLevel = userLevel;
    }
}
