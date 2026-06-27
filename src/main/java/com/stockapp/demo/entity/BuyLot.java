package com.stockapp.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.UUID;

@Entity
public class BuyLot {
    @Id
//    @GeneratedValue
    private UUID id;

    private String ticker;

    private float originalQuantity;
    private float remianingQuantity;

    private float unitPrice;

    private Date tradeDate;

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public float getOriginalQuantity() {
        return originalQuantity;
    }

    public void setOriginalQuantity(float originalQuantity) {
        this.originalQuantity = originalQuantity;
    }

    public float getRemianingQuantity() {
        return remianingQuantity;
    }

    public void setRemianingQuantity(float remianingQuantity) {
        this.remianingQuantity = remianingQuantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
