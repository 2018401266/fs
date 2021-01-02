package com.bean;

public class Charts {
    private double chartsValue;
    private String type;

    public Charts() {
    }

    public Charts(String type, double chartsValue) {
        this.chartsValue = chartsValue;
        this.type = type;
    }

    public double getChartsValue() {
        return chartsValue;
    }

    public void setChartsValue(double chartsValue) {
        this.chartsValue = chartsValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
