package com.uts.restaurant.model;

public class Customer extends User {
    public Customer(String fname, String surname, String email, String phoneNo, Boolean isActive) {
        super(fname, surname, email, phoneNo, isActive);
    }
    
    public Customer(String fname, String surname, String email, String phoneNo) {
        super(fname, surname, email, phoneNo);
    }
}