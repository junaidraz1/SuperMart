package com.example.s3supermart.Model;

public class ViewCartClass {
    private String productName;
    private String ProductQuantity;
    private String ProductPrice;
    private int productImageUri;

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

    public int getProductImageUri() {
        return productImageUri;
    }

    public void setProductImageUri(int productImageUri) {
        this.productImageUri = productImageUri;
    }

    public ViewCartClass(String productName, String productQuantity, String productPrice, int productImageUri) {
        this.productName = productName;
        ProductQuantity = productQuantity;
        ProductPrice = productPrice;
        this.productImageUri = productImageUri;
    }
}
