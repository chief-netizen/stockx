package com.stockapp.demo.dto;


public class AnalysisResponse {
    private long id;
    private String ticker;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private int quantity;
    private float avgprice;
    private float profit;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(float avgprice) {
        this.avgprice = avgprice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }
}
