package com.stockapp.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("stocks")
public class StockController {
    private stockAddService add;
    private StockRetrieveService retrieve;

    public StockController(stockAddService add,StockRetrieveService retrieve){
        this.add=add;
        this.retrieve=retrieve;
    }

    @PostMapping("/add")
    public stocks AddStocks(@RequestBody  stocks values){
        return add.addValues(values);
    }

    @PostMapping("/getStocks")
    public List<stocks> retrieveStocks(@RequestBody StockRequestDto dto){
        return retrieve.retrieveStocks(dto.getStockname());
    }


}
