package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;

public interface StockService {
    Stock createStock(Stock stockEntity);

    boolean deleteStock(String symbol);

    Stock updateStock(String symbol, Stock stockEntity);
}
