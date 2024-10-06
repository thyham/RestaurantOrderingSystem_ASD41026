package com.uts.restaurant.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    Order order;
    Product product;
    String customisation;
    int quantity;

    public OrderItem(Order order, Product product, String customisation, int quantity) {
        this.order = order;
        this.product = product;
        this.customisation = customisation;
        this.quantity = quantity;
    }

    public OrderItem(Product product, String customisation, int quantity) {
        this.product = product;
        this.customisation = customisation;
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }
    
    public String getCustomisation() {
        return customisation;
    }

    public int getQuantity() {
        return quantity;
    }
}
