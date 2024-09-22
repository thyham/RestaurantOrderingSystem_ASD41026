package com.uts.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    ArrayList<User> users;

    public Users(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
