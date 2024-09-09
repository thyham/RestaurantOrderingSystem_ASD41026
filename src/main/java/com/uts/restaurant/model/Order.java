package com.uts.restaurant.model;

public class Order {
    private String productName;
    private String customisationRequest;
    private int quantity;

    public Order(String productName, String customisationRequest, int quantity) {
        this.productName = productName;
        this.customisationRequest = customisationRequest;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomisationRequest() {
        return customisationRequest;
    }

    public void setCustomisationRequest(String customisationRequest) {
        this.customisationRequest = customisationRequest;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
