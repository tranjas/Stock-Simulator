package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}
