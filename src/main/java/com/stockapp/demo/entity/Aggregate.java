package com.stockapp.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;

import java.util.UUID;

@Entity
@Transactional
public class Aggregate {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String ticker;
    private float totalprice;
    private float quantity;
    private float avgprice;
    private float profit;

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public float getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(float avgprice) {
        this.avgprice = avgprice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float toalprice) {
        this.totalprice = toalprice;
    }
}
