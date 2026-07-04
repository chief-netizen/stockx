package com.stockapp.demo.Service;


import com.stockapp.demo.dto.AnalysisResponse;
import com.stockapp.demo.entity.Transactions;
import com.stockapp.demo.repository.TransactionRepository;
import com.stockapp.demo.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private ReportService reportService;

    @Test
    public void StockService_CreateReport_ReturnsAnalysisResponseDto() throws Exception{


        Date tradeDate = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-03");
        Date tradeDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-04");

        Transactions buy1=Transactions.builder()
                .ticker("NOT APPLE")
                .unitPrice(100)
                .tradeDate(tradeDate)
                .transactionType("BUY")
                .quantity(10)
                .build();

        Transactions buy2=Transactions.builder()
                .ticker("NOT APPLE")
                .unitPrice(120)
                .tradeDate(tradeDate2)
                .transactionType("BUY")
                .quantity(10)
                .build();

        Transactions sell=Transactions.builder()
                .ticker("NOT APPLE")
                .unitPrice(130)
                .tradeDate(tradeDate2)
                .transactionType("SELL")
                .quantity(11)
                .build();

        when(transactionRepository.findTransactionByType("NOT APPLE","BUY"))
                .thenReturn(java.util.List.of(buy1,buy2));

        when(transactionRepository.findTransactionByType("NOT APPLE","SELL"))
                .thenReturn(java.util.List.of(sell));

        AnalysisResponse response= reportService.Report("NOT APPLE");

        Assertions.assertEquals(310.0,response.getProfit());
        Assertions.assertEquals(13.333333015441895,response.getAvgprice());


    }


}
