package com.stockapp.demo.repository;

import com.stockapp.demo.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, UUID> {

    @Query("select s from Transactions s where s.ticker=:ticker order by s.tradeDate")
    List<Transactions> findStockByNameList(@Param("ticker")String ticker);

    @Query("Select p from Transactions p where p.ticker=:ticker")
    Transactions findByTicker(@Param("ticker")String ticker);

//    @Query("Select s.quantity from Transactions s where s.ticker=:ticker and s.transactionType=:type order by s.tradeDate")
//    List<Float> findStocksQuantityByName(@Param("ticker")String ticker, @Param("type")String type);
//
//    @Query("Select s.unitPrice from Transactions s where s.ticker=:ticker and s.transactionType=:type order by s.tradeDate")
//    List<Float> findStocksPriceByName(@Param("ticker")String ticker, @Param("type")String type);

    @Query("select t from Transactions t where t.ticker=:ticker and t.transactionType=:type order by t.tradeDate ")
    List<Transactions> findTransactionByType(@Param("ticker")String ticker,@Param("type")String type);

}
