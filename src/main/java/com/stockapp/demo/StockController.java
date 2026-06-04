package com.stockapp.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("stocks")
public class StockController {
    private stockAddService add;
    private StockRetrieveService retrieve;
    private StockSellService sell;


    public StockController(stockAddService add,StockRetrieveService retrieve,StockSellService sell){
        this.add=add;
        this.retrieve=retrieve;
        this.sell=sell;
    }

    @PostMapping("/add")
    public stocks AddStocks(@RequestBody  stocks values){
        return add.addValues(values);
    }

    @PostMapping("/getStocks")
    public StockResponseDto retrieveStocks(@RequestBody StockRequestDto dto){
        return retrieve.retrieveStocks(dto.getStockname());
    }

    @PostMapping("/sellStocks")
    public void sellStocks(@RequestBody StockSellDto dto){
        sell.sellStock(dto.getStockname(),dto.getSellingprice(),dto.getQuantity());
    }


}
