package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Repositories.StockRepository;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
}
