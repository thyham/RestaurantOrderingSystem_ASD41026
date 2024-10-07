package com.uts.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {
    ArrayList<Order> orders;

    public Orders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
}
