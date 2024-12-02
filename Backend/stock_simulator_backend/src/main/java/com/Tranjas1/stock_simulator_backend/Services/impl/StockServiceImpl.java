package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Repositories.PortfolioRepository;
import com.Tranjas1.stock_simulator_backend.Repositories.StockRepository;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final PortfolioService portfolioService;

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, PortfolioService portfolioService, PortfolioRepository portfolioRepository) {
        this.stockRepository = stockRepository;
        this.portfolioService = portfolioService;
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public Stock getStock(long id, String symbol) {
        Optional<Stock> stockOpt = stockRepository.findByUserIdAndSymbol(id, symbol);
        return stockOpt.orElse(null);
    }
    @Override
    public Stock createStock(long id, String symbol, double amount) {
        Portfolio portfolio = portfolioService.getPortfolio(id);
        Optional<Stock> stockOpt = stockRepository.findByUserIdAndSymbol(id, symbol);
        if (stockOpt.isPresent()) throw new EntityNotFoundException("Stock already exists");
        Stock stock = new Stock();
        stock.setAmount(amount);
        stock.setPortfolio(portfolio);
        stock.setSymbol(symbol);
        portfolio.getStocks().add(stock);
        portfolioRepository.save(portfolio);
        return stockRepository.save(stock);
    }

    @Override
    public boolean deleteStock(long id, String symbol) {
        Optional<Stock> stockOpt = stockRepository.findByUserIdAndSymbol(id, symbol);
        Portfolio portfolio = portfolioService.getPortfolio(id);
        if (stockOpt.isPresent()) {
            Stock stock = stockOpt.get();
            portfolio.getStocks().remove(stock);
            portfolioRepository.save(portfolio);
            stockRepository.delete(stock);
            return true;
        }
        return false;
    }

    @Override
    public Stock updateStock(long id, String symbol, double amount) {
        Optional<Stock> stockOpt = stockRepository.findByUserIdAndSymbol(id, symbol);
        if(stockOpt.isEmpty()) throw new EntityNotFoundException("Stock with Id or Symbol: " + id + " " + symbol);
        Stock stock = stockOpt.get();
        if (stock.getAmount() + amount < 0.0) throw new EntityNotFoundException("Not enough funds");
        else if (stock.getAmount() + amount == 0.0) deleteStock(id, symbol);
        stock.setAmount(stock.getAmount() + amount);
        return stockRepository.save(stock);
    }
}
