package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.TransactionDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(path = "/transaction/{id}/{symbol}/{action}/{amount}/{total_price}")
    public ResponseEntity<TransactionDTO> createTransaction(@PathVariable long id, @PathVariable String symbol, @PathVariable String action,
                                                            @PathVariable double amount, @PathVariable double total_price) {
        Transaction transaction = transactionService.createTransaction(id, symbol,action, amount, total_price);
        return new ResponseEntity<>(transactionMapper.mapToDTO(transaction), HttpStatus.CREATED);
    }
}
