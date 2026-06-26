package com.Chief.stockx.service;

import com.Chief.stockx.entity.Price;
import com.Chief.stockx.repository.PriceRepository;
import com.Chief.stockx.repository.StockRepository;
import com.Chief.stockx.dto.StockResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StockRetrieveService {
    private StockRepository repository;
    private PriceRepository pricerepository;

    public StockRetrieveService(StockRepository repository,PriceRepository pricerepository){
        this.pricerepository=pricerepository;
        this.repository=repository;
    }

    public StockResponseDto retrieveStocks(String name){
        Price obj=pricerepository.findStockByName(name);
        StockResponseDto responseDto = new StockResponseDto();
        responseDto.setStockname(name);
        responseDto.setQuantity(obj.getQuantity());
        responseDto.setAvgprice(obj.getAvgprice());
        responseDto.setProfit(obj.getProfit());

        return responseDto;
    }
}
