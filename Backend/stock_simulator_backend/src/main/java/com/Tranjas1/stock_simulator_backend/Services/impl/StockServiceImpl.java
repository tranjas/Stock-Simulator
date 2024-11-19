package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Repositories.StockRepository;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public Stock createStock(Stock stockEntity) {
        return stockRepository.save(stockEntity);
    }

    @Override
    public boolean deleteStock(String symbol) {
        Optional<Stock> stock = stockRepository.findById(symbol);
        if(stock.isEmpty()) throw new NoSuchElementException("Not a valid stock");
        stockRepository.deleteById(symbol);
        return stockRepository.findById(symbol).isEmpty();
    }

    @Override
    public Stock updateStock(String symbol, Stock stockEntity) {
        Optional<Stock> stock = stockRepository.findById(symbol);
        if(stock.isEmpty()) throw new NoSuchElementException("Not a valid stock");
        if (stockEntity.getAmount() != 0.0) {
            stock.get().setAmount(stockEntity.getAmount());
        } else {
            Portfolio portfolio = stock.get().getPortfolio();
            if (portfolio != null) {
                portfolio.getStocks().remove(stockEntity);
                stock.get().setPortfolio(null);
                // TODO: Figure out if I should import Portfolio Endpoint to save the portfolio or to import the Portfolio Repository beneath
            }
        }
        if (stockEntity.getPrice() != 0.0) {
            stock.get().setPrice(stockEntity.getPrice());
        }

        if (stockEntity.getLastUpdated() != null) {
            stock.get().setLastUpdated(stockEntity.getLastUpdated());
        }

        return stockRepository.save(stock.get());
    }
}
