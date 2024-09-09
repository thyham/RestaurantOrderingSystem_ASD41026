package com.uts.restaurant.model;

public class Staff extends User{
    public Staff(String fname, String surname, String email, String phoneNo, Boolean isActive) {
        super(fname, surname, email, phoneNo, isActive);
    }

    public Staff(String fname, String surname, String email, String phoneNo) {
        super(fname, surname, email, phoneNo);
    }
}