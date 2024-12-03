package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends CrudRepository<Stock, String> {
    @Query("SELECT s FROM Stock s WHERE s.portfolio.user.id = :userId AND s.symbol = :symbol")
    Optional<Stock> findByUserIdAndSymbol(@Param("userId") long userId, @Param("symbol") String symbol);

}
