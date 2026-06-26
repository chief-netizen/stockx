package com.stockapp.demo.service;

import com.stockapp.demo.entity.Aggregate;
import com.stockapp.demo.entity.BuyLot;
import com.stockapp.demo.entity.Transactions;
import com.stockapp.demo.repository.AggregateRepository;
import com.stockapp.demo.repository.BuyLotRepository;
import com.stockapp.demo.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StockSellService {

    private TransactionRepository transactionRepository;
    private AggregateRepository aggregateRepository;
    private BuyLotRepository buyLotRepository;

    public StockSellService(TransactionRepository transactionRepository, AggregateRepository aggregateRepository, BuyLotRepository buyLotRepository){
        this.transactionRepository = transactionRepository;
        this.aggregateRepository = aggregateRepository;
        this.buyLotRepository=buyLotRepository;
    }

    public String sellStock(String name,float sellingprice,int quantity){
        List<BuyLot> stockList = buyLotRepository.findStockByNameList(name);
        float profit=0;
        System.out.println("Quantity = " + quantity);
        Aggregate obj= aggregateRepository.findticker(name);
        for(BuyLot stock:stockList){

            int availableShares=stock.getRemianingQuantity();

            if(quantity<=0){
                break;
            }
            else{
                if(stock.getRemianingQuantity()>=quantity){
                    float profitPerShare=sellingprice-stock.getUnitPrice();
                    profit=(quantity*(profitPerShare));
                    availableShares=stock.getRemianingQuantity()-quantity;

                    stock.setRemianingQuantity(availableShares);

                    buyLotRepository.save(stock);

                    obj.setProfit(profit+obj.getProfit());
                    int remainingInPrice =obj.getQuantity()-quantity;
                    obj.setQuantity(remainingInPrice);

                    float totalInPrice= obj.getTotalprice()-(quantity*stock.getUnitPrice());
                    obj.setTotalprice(totalInPrice);
                    obj.setAvgprice(totalInPrice/remainingInPrice);
                    
                    aggregateRepository.save(obj);
                    break;
                }
                else{
                    int originalquanity=stock.getRemianingQuantity();

                    while(availableShares>0 && quantity >0){
                        quantity--;
                        availableShares--;
                    }
                    int sharesold=originalquanity-availableShares;
                    stock.setRemianingQuantity(availableShares);

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
                    buyLotRepository.save(stock);
                    aggregateRepository.save(obj);
                }
            }


        }
        return "sold stock successfully";
    }
}
