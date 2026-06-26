package com.stockapp.demo.repository;

import com.stockapp.demo.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions,Long> {

    @Query("select s from Transactions s where s.ticker=:ticker order by s.tradeDate")
    List<Transactions> findStockByNameList(@Param("ticker")String ticker);

    @Query("Select p from Transactions p where p.ticker=:ticker")
    Transactions findByTicker(@Param("ticker")String ticker);
}
