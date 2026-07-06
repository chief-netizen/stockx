package com.stockapp.demo.repository;

import com.stockapp.demo.entity.Transactions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

    @BeforeEach
    void cleanDatabase() {
        transactionRepository.deleteAll();
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void TransactionRepository_ShouldSaveTransaction() throws ParseException {
        Date tradeDate = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-03");
        //Arrange
        Transactions transactions=Transactions.builder().ticker("META").transactionType("BUY").unitPrice(120)
                .quantity(12)
                .tradeDate(tradeDate)
                .build();

        //Act
        Transactions savedTransactions=transactionRepository.save(transactions);

        //Assert
        Assertions.assertNotNull(savedTransactions);
        Assertions.assertNotNull(savedTransactions.getId());


    }

    @Test
    public void TransactionRepository_ShouldReturnMoreThanOneTransactions() throws ParseException{
        Date tradeDate = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-03");
        Date tradeDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-04");

        //Arrange
        Transactions transaction1=Transactions.builder().ticker("META").transactionType("BUY").unitPrice(120)
                .quantity(12)
                .tradeDate(tradeDate)
                .build();

        Transactions transaction2=Transactions.builder().ticker("META").transactionType("BUY").unitPrice(120)
                .quantity(12)
                .tradeDate(tradeDate)
                .build();

        //Act
        Transactions savedTransaction1=transactionRepository.save(transaction1);
        Transactions savedTransaction2=transactionRepository.save(transaction2);

        List<Transactions> allTransactions=transactionRepository.findAll();

        Assertions.assertEquals(2,allTransactions.size());

    }

    @Test
    public void TransactionRepository_shouldReturnType() throws ParseException{
        Date tradeDate = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-03");
        Date tradeDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2026-07-04");

        //Arrange
        Transactions transaction1=Transactions.builder().ticker("META").transactionType("BUY").unitPrice(120)
                .quantity(12)
                .tradeDate(tradeDate)
                .build();

        Transactions transaction2=Transactions.builder().ticker("META").transactionType("SELL").unitPrice(120)
                .quantity(12)
                .tradeDate(tradeDate)
                .build();

        //Act
        Transactions savedTransaction1=transactionRepository.save(transaction1);
        Transactions savedTransaction2=transactionRepository.save(transaction2);
        List<Transactions> Buytransactions=transactionRepository.findTransactionByType("META","BUY");
        List<Transactions> Selltransactions=transactionRepository.findTransactionByType("META","SELL");

        //Assert
        Assertions.assertEquals("BUY",Buytransactions.get(0).getTransactionType());
        Assertions.assertEquals("SELL",Selltransactions.get(0).getTransactionType());



    }
}
