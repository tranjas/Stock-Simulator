package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import org.springframework.stereotype.Service;

@Service
public interface StockService {
    Stock getStock(long id, String symbol);

    Stock createStock(long id, String symbol, double amount);

    boolean deleteStock(long id, String symbol);

    Stock updateStock(long id, String symbol, double amount);
}
