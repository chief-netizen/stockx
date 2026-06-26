package com.Chief.stockx.controller;

import com.Chief.stockx.dto.StockResponseDto;
import com.Chief.stockx.dto.StockSellDto;
import com.Chief.stockx.entity.Stocks;
import com.Chief.stockx.service.StockRetrieveService;
import com.Chief.stockx.service.StockSellService;
import com.Chief.stockx.service.stockAddService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class StockController {
    private stockAddService add;
    private StockRetrieveService retrieve;
    private StockSellService sell;


    public StockController(stockAddService add,StockRetrieveService retrieve,StockSellService sell){
        this.add=add;
        this.retrieve=retrieve;
        this.sell=sell;
    }

    @PostMapping("/buy")
    public Stocks AddStocks(@RequestBody Stocks values){
        return add.addValues(values);
    }

    @GetMapping("/{stockname}")
    public StockResponseDto retrieveStocks(@PathVariable String stockname){
        return retrieve.retrieveStocks(stockname);
    }

    @PostMapping("/{stockname}/sell")
    public void sellStocks(@PathVariable String stockname,@RequestBody StockSellDto dto){
        sell.sellStock(stockname,dto.getSellingprice(),dto.getQuantity());
    }


}
