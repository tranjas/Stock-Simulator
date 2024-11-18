package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.StockDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {
    private final StockService stockService;
    private final Mapper<Stock, StockDTO> stockMapper;

    public StockController(StockService stockService, Mapper<Stock, StockDTO> stockMapper) {
        this.stockMapper = stockMapper;
        this.stockService = stockService;
    }
}
