package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.TransactionDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Mappers.impl.TransactionMapperImpl;
import com.Tranjas1.stock_simulator_backend.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private final TransactionService transactionService;
    private final Mapper<Transaction, TransactionDTO> transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, Mapper<Transaction, TransactionDTO> transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }
}
