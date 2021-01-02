package com.bean;

public class Product {
    private String productId;
    private String productName;
    private double productYield;//收益率

    public Product() {
    }

    public Product(String productId, String productName, double productYield) {
        this.productId = productId;
        this.productName = productName;
        this.productYield = productYield;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductYield() {
        return productYield;
    }

    public void setProductYield(double productYield) {
        this.productYield = productYield;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId +
                ", productName='" + productName + '\'' +
                ", productYield=" + productYield +
                '}';
    }
}
