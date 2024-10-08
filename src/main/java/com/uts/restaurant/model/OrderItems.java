package com.uts.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderItems implements Serializable {
    ArrayList<OrderItem> orderItems;

    public OrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
