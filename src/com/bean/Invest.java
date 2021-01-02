package com.bean;

public class Invest {
    private String accountId;
    private String productId;
    private double investValue;

    public Invest() {
    }

    public Invest(String accountId, String productId, double investValue) {
        this.accountId = accountId;
        this.productId = productId;
        this.investValue = investValue;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getInvestValue() {
        return investValue;
    }

    public void setInvestValue(double investValue) {
        this.investValue = investValue;
    }

    @Override
    public String toString() {
        return "Invest{" +
                "accountId=" + accountId +
                ", productId='" + productId + '\'' +
                ", investValue=" + investValue +
                '}';
    }
}
