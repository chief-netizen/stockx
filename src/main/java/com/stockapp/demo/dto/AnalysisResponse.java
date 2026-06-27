package com.stockapp.demo.dto;


import java.util.UUID;

public class AnalysisResponse {
    private UUID id;
    private String ticker;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private float quantity;
    private float avgprice;
    private float profit;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
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
