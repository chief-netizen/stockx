package com.stockapp.demo.service;

import com.stockapp.demo.dto.CreateTransactionRequest;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.entity.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private TransactionRepository repository1;


    public TransactionService(TransactionRepository repository1){
        this.repository1=repository1;
    }

    public ResponseEntity<String> addValues(CreateTransactionRequest transactions){
        String ticker = transactions.getTicker();

        Transactions transactionsobj=Transactions.builder()
                        .tradeDate(transactions.getTradeDate())
                                .ticker(transactions.getTicker())
                                        .quantity(transactions.getQuantity())
                                                .transactionType(transactions.getTransactiontype())
                                                        .unitPrice(transactions.getUnitPrice())
                                                                .build();


        repository1.save(transactionsobj);

        return ResponseEntity.ok("Action done");


    }
}
