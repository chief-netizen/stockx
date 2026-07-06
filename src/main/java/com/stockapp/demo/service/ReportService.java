package com.stockapp.demo.service;

import com.stockapp.demo.dto.AnalysisResponse;
import com.stockapp.demo.entity.Transactions;
import com.stockapp.demo.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    TransactionRepository transactionRepository;
    public ReportService(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }

    public AnalysisResponse Report(String ticker){
        List<Transactions> transactionsSellList=transactionRepository.findTransactionByType(ticker, "SELL");
        List<Transactions> transactionsBuyList=transactionRepository.findTransactionByType(ticker,"BUY");
        float profit=0;
        int l=0;
        int r=0;
        float priceOfRemaining =0;
        float quantityRemaining=0;
        while(r<transactionsSellList.size()){
            float currentSellQuantity=transactionsSellList.get(r).getQuantity();
            float currentBuyQuantity=transactionsBuyList.get(l).getQuantity();
            float currentSellPrice=transactionsSellList.get(r).getUnitPrice();
            float currentBuyPrice=transactionsBuyList.get(l).getUnitPrice();

            if(currentSellQuantity>currentBuyQuantity){
                profit=profit+currentBuyQuantity*(currentSellPrice-currentBuyPrice);
                float difference=Math.abs(transactionsBuyList.get(l).getQuantity()-transactionsSellList.get(r).getQuantity());
                transactionsSellList.get(r).setQuantity(difference);
                transactionsBuyList.get(l).setQuantity(0);
                l++;
            }
            else{
                float buyQuantityOriginal=transactionsBuyList.get(l).getQuantity();
                float difference=Math.abs(transactionsBuyList.get(l).getQuantity()-transactionsSellList.get(r).getQuantity());
                profit=profit+currentSellQuantity*(currentSellPrice-currentBuyPrice);
                transactionsSellList.get(r).setQuantity(0);
                transactionsBuyList.get(l).setQuantity(difference);
                r++;
            }


        }
        while(l<transactionsBuyList.size()){
            quantityRemaining=quantityRemaining+transactionsBuyList.get(l).getQuantity();
            priceOfRemaining = priceOfRemaining +(transactionsBuyList.get(l).getQuantity()*transactionsBuyList.get(l).getUnitPrice());
            l++;
        }
        AnalysisResponse analysisResponse=AnalysisResponse.builder().ticker(ticker).profit(profit)
                .quantity(quantityRemaining)
                .avgprice(priceOfRemaining/quantityRemaining).build();

        return analysisResponse;
    }
}
