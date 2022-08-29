package com.example.s3supermart.Model;

public class OrdersExpandableLayoutModelClass {
    private String productName;
    private String productPrice;
    int productImage;

    public OrdersExpandableLayoutModelClass(String productName, String productPrice, int productImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }
}
