package com.stockapp.demo.steps;

import com.stockapp.demo.dto.AnalysisResponse;
import com.stockapp.demo.dto.CreateTransactionRequest;
import com.stockapp.demo.entity.Transactions;
import com.stockapp.demo.service.ReportService;
import com.stockapp.demo.service.TransactionService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TransactionSteps {


    ReportService reportService;
    TransactionService transactionService;

    public TransactionSteps(ReportService reportService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.reportService = reportService;
    }

    @Given("the following stocks exist")
    public void addTransactions(DataTable table) {

        List<Map<String, String>> rows = table.asMaps();

        for (Map<String, String> row : rows) {
            LocalDate localDate = LocalDate.parse(row.get("tradeDate"));
            Date date = java.sql.Date.valueOf(localDate);
            CreateTransactionRequest transactions = CreateTransactionRequest.builder()
                    .ticker(row.get("ticker"))
                    .quantity(Float.parseFloat(row.get("quantity")))
                    .tradeDate(date)
                    .unitPrice(Float.parseFloat(row.get("unitPrice")))
                    .transactionType(row.get("tradeType"))
                    .build();

            transactionService.addValues(transactions);
        }
    }

    @Then("ticker {string} should have {float} AveragePrice and {float} Profit")
    public void verifyProfitAndAverage(String ticker, float avg, float profit) {
        AnalysisResponse response = reportService.Report(ticker);
        Assertions.assertEquals(avg, response.getAvgprice(), 0.01f);
        Assertions.assertEquals(profit, response.getProfit(), 0.01f);
    }


}
