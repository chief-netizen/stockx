package com.stockapp.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StockRetrieveService {
    private StockRepository repository;

    public StockRetrieveService(StockRepository repository){
        this.repository=repository;
    }

    public List<stocks> retrieveStocks(String name){
//        System.out.println(repository.findStockByName(name));
        return repository.findStockByName(name);
    }
}
