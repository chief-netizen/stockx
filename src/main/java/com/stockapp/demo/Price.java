package com.stockapp.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;

@Entity
@Transactional
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String stockname;
    private float totalprice;
    private int quantity;
    private float avgprice;

    public float getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(float avgprice) {
        this.avgprice = avgprice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float toalprice) {
        this.totalprice = toalprice;
    }
}
