package com.uts.restaurant.model;

import java.io.Serializable;

public class AccessLog implements Serializable {
    public User user;
    public String date;
    public String desc;

    public AccessLog(User user, String date, String desc) {
        this.user = user;
        this.date = date;
        this.desc = desc;
    }

    public User getUser() {
        return user;
    }
    public String getDate() {
        return date;
    }
    public String getDesc() {
        return desc;
    }
}
