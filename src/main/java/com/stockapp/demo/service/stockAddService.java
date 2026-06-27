package com.stockapp.demo.service;

import com.stockapp.demo.dto.CreateTransactionRequest;
import com.stockapp.demo.entity.Aggregate;
import com.stockapp.demo.entity.BuyLot;
import com.stockapp.demo.repository.AggregateRepository;
import com.stockapp.demo.repository.BuyLotRepository;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.entity.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class stockAddService {
    private TransactionRepository repository1;
    private AggregateRepository aggregateRepository;
    private BuyLotRepository buyLotRepository;

    public stockAddService(TransactionRepository repository1, AggregateRepository aggregateRepository, BuyLotRepository buyLotRepository){
        this.aggregateRepository = aggregateRepository;
        this.repository1=repository1;
        this.buyLotRepository=buyLotRepository;
    }

    public ResponseEntity<String> addValues(CreateTransactionRequest transactions){
        String ticker = transactions.getTicker();
        Aggregate aggregateObj = aggregateRepository.findticker(ticker);
        if(aggregateObj ==null){
            float currentPrice= transactions.getQuantity()* transactions.getUnitPrice();
            Aggregate obj1=new Aggregate();
            obj1.setTicker(ticker);
            obj1.setTotalprice(currentPrice);
            obj1.setQuantity(transactions.getQuantity());
            obj1.setAvgprice(currentPrice/ transactions.getQuantity());
            obj1.setProfit(0);
            obj1.setId(UUID.fromString(transactions.getId()));
            aggregateRepository.save(obj1);
        }
        else{
            float currentPrice= transactions.getQuantity()* transactions.getUnitPrice();
            float previousPrice= aggregateObj.getTotalprice();
            float previousQuantity= aggregateObj.getQuantity();
            float currentQuantity=previousQuantity+ transactions.getQuantity();
            aggregateObj.setQuantity(currentQuantity);
            aggregateObj.setTotalprice(previousPrice+currentPrice);
            aggregateObj.setAvgprice((previousPrice+currentPrice)/currentQuantity);
            aggregateObj.setId(UUID.fromString(transactions.getId()));
            aggregateRepository.save(aggregateObj);
        }
        BuyLot buyLotObj=new BuyLot();
        buyLotObj.setId(UUID.fromString(transactions.getId()));
        buyLotObj.setTicker(ticker);
        buyLotObj.setUnitPrice(transactions.getUnitPrice());
        buyLotObj.setOriginalQuantity(transactions.getQuantity());
        buyLotObj.setRemianingQuantity(transactions.getQuantity());
        buyLotObj.setTradeDate(transactions.getTradeDate());
        buyLotRepository.save(buyLotObj);


        return ResponseEntity.ok("stocks bought successfully");


    }
}
