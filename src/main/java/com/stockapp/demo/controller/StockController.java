package com.stockapp.demo.controller;

import com.stockapp.demo.dto.AnalysisResponse;
import com.stockapp.demo.dto.CreateTransactionRequest;
import com.stockapp.demo.entity.Transactions;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.service.StockRetrieveService;
import com.stockapp.demo.service.StockSellService;
import com.stockapp.demo.service.stockAddService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("transactions")
public class StockController {
    private stockAddService add;
    private StockRetrieveService retrieve;
    private StockSellService sell;
    private TransactionRepository rep;


    public StockController(stockAddService add,StockRetrieveService retrieve,StockSellService sell,TransactionRepository rep){
        this.add=add;
        this.retrieve=retrieve;
        this.sell=sell;
        this.rep=rep;
    }

    @PostMapping()
    public ResponseEntity<String> AddStocks(@RequestBody CreateTransactionRequest values){
        if(values.getTransactiontype().equals("SELL")){
            Transactions obj=new Transactions();
            obj.setTicker(values.getTicker());
            obj.setTransactionType(values.getTransactiontype());
            obj.setTradeDate(values.getTradeDate());
            obj.setQuantity(values.getQuantity());
            obj.setUnitPrice(values.getUnitPrice());
            obj.setId(UUID.fromString(values.getId()));
            rep.save(obj);
            return sell.sellStock(values.getTicker(), values.getUnitPrice(), values.getQuantity());
        }
        else{
            Transactions obj=new Transactions();
            obj.setTicker(values.getTicker());
            obj.setTradeDate(values.getTradeDate());
            obj.setTransactionType(values.getTransactiontype());
            obj.setQuantity(values.getQuantity());
            obj.setUnitPrice(values.getUnitPrice());
            obj.setId(UUID.fromString(values.getId()));
            rep.save(obj);
            return add.addValues(values);
        }

    }

    @GetMapping("/{stockname}")
    public AnalysisResponse retrieveStocks(@PathVariable String stockname){
        return retrieve.retrieveStocks(stockname);
    }

//    @PostMapping("/{stockname}")
//    public void sellStocks(@PathVariable String stockname,@RequestBody CreateTransactionRequest dto){
//        Transactions obj=new Transactions();
//        obj.setTicker(stockname);
//        obj.setTransactionType(dto.getType());
//        obj.setQuantity(dto.getQuantity());
//        obj.setUnitPrice(dto.getUnitPrice());
//        rep.save(obj);
//        sell.sellStock(stockname,dto.getUnitPrice(),dto.getQuantity());
//    }


}
