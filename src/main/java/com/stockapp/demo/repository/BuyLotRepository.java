package com.stockapp.demo.repository;

import com.stockapp.demo.entity.BuyLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BuyLotRepository extends JpaRepository<BuyLot, UUID> {

    @Query("select s from BuyLot s where s.ticker=:ticker order by s.tradeDate")
    List<BuyLot> findStockByNameList(@Param("ticker")String ticker);

    @Query("Select p from BuyLot p where p.ticker=:ticker")
    BuyLot findByTicker(@Param("ticker")String ticker);

}
