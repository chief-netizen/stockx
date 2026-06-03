package com.stockapp.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<stocks,Long> {

    @Query("select s from stocks s where s.stockname=:stockname order by date")
    List<stocks> findStockByName(@Param("stockname")String name);
}
