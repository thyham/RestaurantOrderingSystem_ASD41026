package com.uts.restaurant.model;

public class Staff extends User{
    public Staff(int id, String fname, String surname, String email, String phoneNo, Boolean isActive) {
        super(id, fname, surname, email, phoneNo, isActive);
    }

    public Staff(int id, String email) {
        super(id, email);
    }
}