package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    Transaction createTransaction(long id, String symbol, String action, double amount, double total_price);
}
