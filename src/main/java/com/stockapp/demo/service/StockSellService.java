package com.Chief.stockx.service;

import com.Chief.stockx.entity.Price;
import com.Chief.stockx.repository.PriceRepository;
import com.Chief.stockx.repository.StockRepository;
import com.Chief.stockx.entity.Stocks;
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
        List<Stocks> stockList = stockRepository.findStockByNameList(name);
        float profit=0;
        System.out.println("Quantity = " + quantity);
        Price obj=priceRepository.findStockByName(name);
        for(Stocks stock:stockList){

            int availableShares=stock.getQuantity();

            if(quantity<=0){
                break;
            }
            else{
                if(stock.getQuantity()>=quantity){
                    float profitPerShare=sellingprice-stock.getUnitPrice();
                    profit=(quantity*(profitPerShare));
                    availableShares=stock.getQuantity()-quantity;

                    stock.setQuantity(availableShares);

                    stockRepository.save(stock);

                    obj.setProfit(profit+obj.getProfit());
                    int remainingInPrice =obj.getQuantity()-quantity;
                    obj.setQuantity(remainingInPrice);

                    float totalInPrice= obj.getTotalprice()-(quantity*stock.getUnitPrice());
                    obj.setTotalprice(totalInPrice);
                    obj.setAvgprice(totalInPrice/remainingInPrice);
                    
                    priceRepository.save(obj);
                    break;
                }
                else{
                    int originalquanity=stock.getQuantity();

                    while(availableShares>0 && quantity >0){
                        quantity--;
                        availableShares--;
                    }
                    int sharesold=originalquanity-availableShares;
                    stock.setQuantity(availableShares);

                    profit=sharesold*(sellingprice-stock.getUnitPrice());
                    obj.setProfit(profit+obj.getProfit());
                    int remainingInPrice =obj.getQuantity()-sharesold;
                    obj.setQuantity(remainingInPrice);
                    float totalInPrice= obj.getTotalprice()-(sharesold*stock.getUnitPrice());
                    obj.setTotalprice(totalInPrice);

                    if(remainingInPrice > 0){
                        obj.setAvgprice(totalInPrice / remainingInPrice);
                    }else{
                        obj.setAvgprice(0);
                    }
                    stockRepository.save(stock);
                    priceRepository.save(obj);
                }
            }


        }
        return "sold stock successfully";
    }
}
