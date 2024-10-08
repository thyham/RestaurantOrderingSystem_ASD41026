package com.uts.restaurant.model;

import java.io.Serializable;

public class ProductLog implements Serializable {
    Staff staff;
    String date;
    Product product;
    String desc;

    public ProductLog(Staff staff, String date, Product product, String desc) {
        this.staff = staff;
        this.date = date;
        this.product = product;
        this.desc = desc;
    }

    public Staff getStaff() {
        return staff;
    }
    public String getDate() {
        return date;
    }
    public Product getProduct() {
        return product;
    }
    public String getDesc() {
        return desc;
    }
}
