package com.uts.restaurant.model;

import java.io.Serializable;

public class Order implements Serializable {
    int id;
    Customer customer;
    String date;
    int receiptNo;
    String paymentType;

    public Order(int id, Customer customer, String date, int receiptNo, String payementType) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.receiptNo = receiptNo;
        this.paymentType = payementType;
    }

    public int getID() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDate() {
        return date;
    }

    public int getReceiptNo() {
        return receiptNo;
    }

    public String getPaymentType() {
        return paymentType;
    }

}
