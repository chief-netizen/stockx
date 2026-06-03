package com.stockapp.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {

    @Query("Select p from Price p where p.stockname=:stockname")
    Price findStockByName(@Param("stockname")String name);
}
