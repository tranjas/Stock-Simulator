package com.Tranjas1.stock_simulator_backend.Mappers.impl;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.TransactionDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements Mapper<Transaction, TransactionDTO> {

    private final ModelMapper modelMapper;

    public TransactionMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public TransactionDTO mapToDTO(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public Transaction mapToEntity(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
