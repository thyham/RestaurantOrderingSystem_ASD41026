package com.uts.restaurant.model;

public class Customer extends User {
    public Customer(int id, String fname, String surname, String email, String phoneNo, Boolean isActive) {
        super(id, fname, surname, email, phoneNo, isActive);
    }

    public Customer(int id, String email) {
        super(id, email);
    }
}