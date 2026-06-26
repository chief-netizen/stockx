package com.stockapp.demo.repository;

import com.stockapp.demo.entity.Aggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Aggregate,Long> {

    @Query("Select p from Price p where p.stockname=:stockname")
    Aggregate findStockByName(@Param("stockname")String name);
}
