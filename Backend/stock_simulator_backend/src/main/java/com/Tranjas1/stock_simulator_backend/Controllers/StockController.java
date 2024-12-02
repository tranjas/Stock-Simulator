package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.StockDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StockController {
    private final StockService stockService;
    private final Mapper<Stock, StockDTO> stockMapper;


    public StockController(StockService stockService, Mapper<Stock, StockDTO> stockMapper) {
        this.stockMapper = stockMapper;
        this.stockService = stockService;
    }

    @PostMapping(path = "/stocks/{id}/{symbol}/{amount}")
    public ResponseEntity<StockDTO> createStock(@PathVariable long id, @PathVariable String symbol,@PathVariable double amount) {
        Stock stockSaved = stockService.createStock(id, symbol, amount);
        return new ResponseEntity<>(stockMapper.mapToDTO(stockSaved), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/stocks/{id}/{symbol}")
    public ResponseEntity<Void> deleteStock(@PathVariable long id, @PathVariable String symbol) {
        boolean isDeleted = stockService.deleteStock(id,symbol);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/stocks/{id}/{symbol}/{amount}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable long id, @PathVariable String symbol,@PathVariable double amount) {
        Stock stockSaved = stockService.updateStock(id, symbol, amount);
        return ResponseEntity.ok(stockMapper.mapToDTO(stockSaved));
    }
}