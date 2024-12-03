package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.TransactionRepository;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import com.Tranjas1.stock_simulator_backend.Services.TransactionService;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final PortfolioService portfolioService;

    private final UserService userService;
    private final StockService stockService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, PortfolioService portfolioService, StockService stockService, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.portfolioService = portfolioService;
        this.stockService = stockService;
        this.userService = userService;
    }

    @Override
    public Transaction createTransaction(long id, String symbol, String action, double amount, double total_price) {
        if (amount < 0.0) throw new EntityNotFoundException("Amount can't be negative");
        if(total_price < 0.0) throw new EntityNotFoundException("Total Price can't be negative");

        Transaction transaction = new Transaction();
        Portfolio portfolio = portfolioService.getPortfolio(id);
        User user = userService.getUser(id);
        Stock stock = stockService.getStock(id, symbol);

        if (user == null) throw new EntityNotFoundException("User not found");

        if (action.equals("Buy")) {
            if (total_price > portfolio.getBuyingPower()) throw new EntityNotFoundException("Don't have enough buying power");
            if (stock == null) {
                stockService.createStock(id, symbol, amount);
            } else {
                stockService.updateStock(id, symbol, amount);
            }
            portfolioService.updateBuyingPower(id, total_price * -1);
        } else if (action.equals("Sell")) {
                assert stock != null;
                if (stock.getAmount() < amount) {
                    throw new EntityNotFoundException("Can't sell stock since stock is not present");
                }
                stockService.updateStock(id,symbol, amount * -1);
                portfolioService.updateBuyingPower(id, total_price);
            }
        else {
            throw new EntityNotFoundException("Not a valid method");
        }
        transaction.setUser(user);
        transaction.setSymbol(symbol);
        transaction.setAmount(amount);
        transaction.setTotalPrice(total_price);
        return transactionRepository.save(transaction);
    }
}
