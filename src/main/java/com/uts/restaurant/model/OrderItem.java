package com.uts.restaurant.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    int orderID;
    int productID;
    String customisation;
    int quantity;

    public OrderItem(int orderID, int productID, String customisation, int quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.customisation = customisation;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }
    
    public String getCustomisation() {
        return customisation;
    }

    public int getQuantity() {
        return quantity;
    }
}
