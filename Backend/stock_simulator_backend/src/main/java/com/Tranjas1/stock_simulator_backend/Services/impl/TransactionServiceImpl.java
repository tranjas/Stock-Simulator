package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Repositories.TransactionRepository;
import com.Tranjas1.stock_simulator_backend.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
}
