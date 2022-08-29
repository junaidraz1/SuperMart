package com.example.s3supermart.Model;

import java.io.Serializable;
import java.util.List;

public class OrdersModelClass implements Serializable {

    String productName1;
    String productPrice1;
    String productName2;
    String productPrice2;
    int productImage1;
    int productImage2;
    private List<OrdersExpandableLayoutModelClass> SubItemList;

    public OrdersModelClass(String productName1, String productPrice1, String productName2, String productPrice2,
                            int productImage1, int productImage2, List<OrdersExpandableLayoutModelClass> subItemList) {
        this.productName1 = productName1;
        this.productPrice1 = productPrice1;
        this.productName2 = productName2;
        this.productPrice2 = productPrice2;
        this.productImage1 = productImage1;
        this.productImage2 = productImage2;
        SubItemList = subItemList;
    }

    public String getProductName1() {
        return productName1;
    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public String getProductPrice1() {
        return productPrice1;
    }

    public void setProductPrice1(String productPrice1) {
        this.productPrice1 = productPrice1;
    }

    public String getProductName2() {
        return productName2;
    }

    public void setProductName2(String productName2) {
        this.productName2 = productName2;
    }

    public String getProductPrice2() {
        return productPrice2;
    }

    public void setProductPrice2(String productPrice2) {
        this.productPrice2 = productPrice2;
    }

    public int getProductImage1() {
        return productImage1;
    }

    public void setProductImage1(int productImage1) {
        this.productImage1 = productImage1;
    }

    public int getProductImage2() {
        return productImage2;
    }

    public void setProductImage2(int productImage2) {
        this.productImage2 = productImage2;
    }

    public List<OrdersExpandableLayoutModelClass> getSubItemList() {
        return SubItemList;
    }

    public void setSubItemList(List<OrdersExpandableLayoutModelClass> subItemList) {
        SubItemList = subItemList;
    }
}
