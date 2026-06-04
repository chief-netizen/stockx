package com.stockapp.demo;

import org.springframework.stereotype.Service;

@Service
public class stockAddService {
    private StockRepository repository1;
    private PriceRepository priceRepository;

    public stockAddService(StockRepository repository1, PriceRepository priceRepository){
        this.priceRepository=priceRepository;
        this.repository1=repository1;
    }

    public stocks addValues(stocks stocks){
        String stockname=stocks.getStockname();
        Price priceObj=priceRepository.findStockByName(stockname);
        if(priceObj==null){
            float currentPrice=stocks.getQuantity()* stocks.getPrice();
            Price obj1=new Price();
            obj1.setStockname(stockname);
            obj1.setTotalprice(currentPrice);
            obj1.setQuantity(stocks.getQuantity());
            obj1.setAvgprice(currentPrice/stocks.getQuantity());
            obj1.setProfit(0);
            priceRepository.save(obj1);
        }
        else{
            float currentPrice=stocks.getQuantity()* stocks.getPrice();
            float previousPrice=priceObj.getTotalprice();
            int previousQuantity=priceObj.getQuantity();
            int currentQuantity=previousQuantity+ stocks.getQuantity();
            priceObj.setQuantity(currentQuantity);
            priceObj.setTotalprice(previousPrice+currentPrice);
            priceObj.setAvgprice((previousPrice+currentPrice)/currentQuantity);
            priceRepository.save(priceObj);
        }
        return repository1.save(stocks);
    }
}
