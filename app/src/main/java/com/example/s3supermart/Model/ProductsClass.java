package com.example.s3supermart.Model;

public class ProductsClass {
    private String productName;
    private String ProductQuantity;
    private String ProductPrice;
   /* private String productImageUri;*/

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

    public ProductsClass(String productName, String ProductQuantity, String ProductPrice) {
        this.productName = productName;
        this.ProductQuantity = ProductQuantity;
        this.ProductPrice = ProductPrice;
    }

}

