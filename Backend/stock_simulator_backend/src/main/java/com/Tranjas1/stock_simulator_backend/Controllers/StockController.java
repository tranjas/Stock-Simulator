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

    @PostMapping(path = "/stocks")
    public ResponseEntity<StockDTO> createStock(@RequestBody StockDTO stockDTO) {
        Stock stockEntity = stockMapper.mapToEntity(stockDTO);
        Stock stockSaved = stockService.createStock(stockEntity);
        return new ResponseEntity<>(stockMapper.mapToDTO(stockSaved), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/stocks/{symbol}")
    public ResponseEntity<Void> deleteStock(@PathVariable String symbol) {
        boolean isDeleted = stockService.deleteStock(symbol);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/stocks/{symbol}")
    public ResponseEntity<StockDTO> updateStock(@PathVariable String symbol, @RequestBody StockDTO stockDTO) {
        Stock stockEntity = stockMapper.mapToEntity(stockDTO);
        Stock stockSaved = stockService.updateStock(symbol, stockEntity);
        return ResponseEntity.ok(stockMapper.mapToDTO(stockSaved));
    }
}
