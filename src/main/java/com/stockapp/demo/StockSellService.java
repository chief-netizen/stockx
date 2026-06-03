package com.stockapp.demo;

import org.springframework.stereotype.Service;

@Service
public class StockSellService {

    private StockRepository stockRepository;

    public StockSellService(StockRepository stockRepository){
        this.stockRepository=stockRepository;
    }

    public String sellStock(String name){
        
        return "sold stock successfully";
    }
}
