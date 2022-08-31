package com.example.s3supermart.Model;

public class CheckOutClass {
    private String productName;
    private String ProductQuantity;
    private String ProductPrice;

    public CheckOutClass(String productName, String productQuantity, String productPrice) {
        this.productName = productName;
        ProductQuantity = productQuantity;
        ProductPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }
}
