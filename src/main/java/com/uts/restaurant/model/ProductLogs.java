package com.uts.restaurant.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductLogs implements Serializable {
    ArrayList<ProductLog> productLogs;

    public ProductLogs(ArrayList<ProductLog> productLogs) {
        this.productLogs = productLogs;
    }

    public ArrayList<ProductLog> getProductLogs() {
        return productLogs;
    }

    public void setProductLogs(ArrayList<ProductLog> productLogs) {
        this.productLogs = productLogs;
    }
}
