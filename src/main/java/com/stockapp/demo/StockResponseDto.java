package com.stockapp.demo;


public class StockResponseDto {
    private String stockname;
    private int quantity;
    private float avgprice;
    private float profit;

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
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
