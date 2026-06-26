package com.stockapp.demo.repository;

import com.stockapp.demo.entity.Aggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AggregateRepository extends JpaRepository<Aggregate,Long> {

    @Query("Select a from Aggregate a where a.ticker=:ticker")
    Aggregate findticker(@Param("ticker")String ticker);
}
