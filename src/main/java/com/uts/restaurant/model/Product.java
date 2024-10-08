package com.uts.restaurant.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    int id;
    String name;
    String desc;
    BigDecimal price;

    public Product(int id, String name, String desc, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getID() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    public BigDecimal price() {
        return price;
    }
}
