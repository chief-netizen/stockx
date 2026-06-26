package com.stockapp.demo.service;

import com.stockapp.demo.entity.Aggregate;
import com.stockapp.demo.repository.AggregateRepository;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.dto.AnalysisResponse;
import org.springframework.stereotype.Service;

@Service
public class StockRetrieveService {
    private TransactionRepository repository;
    private AggregateRepository aggregateRepository;

    public StockRetrieveService(TransactionRepository repository, AggregateRepository aggregaterepository){
        this.aggregateRepository = aggregaterepository;
        this.repository=repository;
    }

    public AnalysisResponse retrieveStocks(String name){
        Aggregate obj= aggregateRepository.findticker(name);
        AnalysisResponse responseDto = new AnalysisResponse();
        responseDto.setTicker(name);
        responseDto.setQuantity(obj.getQuantity());
        responseDto.setAvgprice(obj.getAvgprice());
        responseDto.setProfit(obj.getProfit());

        return responseDto;
    }


}
