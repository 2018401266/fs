package com.bean;

import java.util.Date;

public class Budget {
    private Integer budgetId;
    private String memberId;
    private String type;
    private double value;
    private Date budgetDate;
    private String state;

    public Budget() {
    }

    public Budget(Integer budgetId, String memberId, String type, double value, Date budgetDate, String state) {
        this.budgetId = budgetId;
        this.memberId = memberId;
        this.type = type;
        this.value = value;
        this.budgetDate = budgetDate;
        this.state = state;
    }

    public Integer getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Integer budgetId) {
        this.budgetId = budgetId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(Date budgetDate) {
        this.budgetDate = budgetDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId='" + budgetId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", budgetDate=" + budgetDate +
                ", state='" + state + '\'' +
                '}';
    }
}
