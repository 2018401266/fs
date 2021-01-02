package com.bean;

public class Account {
    private String accountId;
    private String accountPwd;
    private double totalAsset;
    private double totalInvest;

    public Account() {
    }

    public Account(String accountId, String accountPwd, double totalAsset, double totalInvest) {
        this.accountId = accountId;
        this.accountPwd = accountPwd;
        this.totalAsset = totalAsset;
        this.totalInvest = totalInvest;
    }

    public Account(String accountId, String accountPwd) {
        this.accountId = accountId;
        this.accountPwd = accountPwd;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public double getTotalAsset() {
        return totalAsset;
    }

    public void setTotalAsset(double totalAsset) {
        this.totalAsset = totalAsset;
    }

    public double getTotalInvest() {
        return totalInvest;
    }

    public void setTotalInvest(double totalInvest) {
        this.totalInvest = totalInvest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountPwd='" + accountPwd +
                ", totalAsset=" + totalAsset +
                ", totalInvest=" + totalInvest +
                '}';
    }
}
