package com.stockapp.demo;

import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StockSellService {

    private StockRepository stockRepository;
    private PriceRepository priceRepository;

    public StockSellService(StockRepository stockRepository,PriceRepository priceRepository){
        this.stockRepository=stockRepository;
        this.priceRepository=priceRepository;
    }

    public String sellStock(String name,float sellingprice,int quantity){
        List<stocks> stockList = stockRepository.findStockByNameList(name);
        float profit=0;
        System.out.println("Quantity = " + quantity);
        for(stocks i:stockList){
            Price obj=priceRepository.findStockByName(name);
//            System.out.println("Quantity = " + quantity);
//            System.out.println("Selling Price = " + sellingprice);
//            System.out.println("Buy Price = " + i.getPrice());
//            System.out.println("Profit = " + profit);
            int remaining=i.getQuantity();
            if(quantity<=0){
                break;
            }
            else{
                if(i.getQuantity()>=quantity){
                    profit=(quantity*(sellingprice-i.getPrice()));
                    remaining=i.getQuantity()-quantity;
                    i.setQuantity(remaining);
                    stockRepository.save(i);
                    obj.setProfit(profit+obj.getProfit());
                    int remainingInPrice =obj.getQuantity()-quantity;
                    obj.setQuantity(remainingInPrice);
                    float totalInPrice= obj.getTotalprice()-(quantity*i.getPrice());
                    obj.setTotalprice(totalInPrice);
                    obj.setAvgprice(totalInPrice/remainingInPrice);
                    priceRepository.save(obj);
                    break;
                }
                else{
                    int originalquanity=i.getQuantity();

                    while(remaining>0 && quantity >0){
                        quantity--;
                        remaining--;
                    }
                    int sharesold=originalquanity-remaining;
                    i.setQuantity(remaining);
                    profit=sharesold*(sellingprice-i.getPrice());
                    obj.setProfit(profit+obj.getProfit());
                    int remainingInPrice =obj.getQuantity()-sharesold;
                    obj.setQuantity(remainingInPrice);
                    float totalInPrice= obj.getTotalprice()-(sharesold*i.getPrice());
                    obj.setTotalprice(totalInPrice);
                    if(remainingInPrice > 0){
                        obj.setAvgprice(totalInPrice / remainingInPrice);
                    }else{
                        obj.setAvgprice(0);
                    }
                    stockRepository.save(i);
                    priceRepository.save(obj);
                }
            }


        }
        return "sold stock successfully";
    }
}
