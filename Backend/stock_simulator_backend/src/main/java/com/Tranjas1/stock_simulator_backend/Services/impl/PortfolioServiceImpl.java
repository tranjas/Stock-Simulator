package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.PortfolioRepository;
import com.Tranjas1.stock_simulator_backend.Repositories.StockRepository;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final StockRepository stockService;
    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, StockRepository stockService) {
        this.portfolioRepository = portfolioRepository;
        this.stockService = stockService;
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolioEntity) {
        return portfolioRepository.save(portfolioEntity);
    }

    @Override
    public boolean deletePortfolio(long user_id) {
        Optional<Portfolio> userOptional = portfolioRepository.findById(user_id);
        if (userOptional.isPresent()) {
            portfolioRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateBuyingPower(long user_id, double amount) {
        Portfolio portfolio = portfolioRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found for user ID: " + user_id));
        if (portfolio.getBuyingPower() + amount < 0.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Buying power will be negative");
        }
        portfolio.setBuyingPower(portfolio.getBuyingPower() + amount);
        portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio getPortfolio(long user_id) {
        return  portfolioRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found for user ID: " + user_id));
    }

    @Override
    public Portfolio updateStock(long user_id, String symbol, String method) {
        Portfolio portfolio = portfolioRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found for user ID: " + user_id));

        Optional<Stock> stock = stockService.findById(symbol);
        if (stock.isEmpty()) throw new EntityNotFoundException("Stock not found for user ID: " + symbol);
        if (method.equals("add")) {
            portfolio.getStocks().add(stock.get());
        } else if(method.equals("remove")) {
            portfolio.getStocks().remove(stock.get());
        }
        return portfolioRepository.save(portfolio);
    }
}
