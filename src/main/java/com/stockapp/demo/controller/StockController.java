package com.stockapp.demo.controller;

import com.stockapp.demo.dto.AnalysisResponse;
import com.stockapp.demo.dto.CreateTransactionRequest;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.service.ReportService;
import com.stockapp.demo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {
    private TransactionService add;
    private TransactionRepository rep;
    private ReportService report;


    public StockController(TransactionService add, TransactionRepository rep, ReportService report){
        this.add=add;
        this.rep=rep;
        this.report=report;
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> AddStocks(@RequestBody CreateTransactionRequest values){

            return add.addValues(values);
    }

    @GetMapping("/report/{ticker}")
    public AnalysisResponse retrieveStocks(@PathVariable String ticker){
        return report.Report(ticker);
    }

}
