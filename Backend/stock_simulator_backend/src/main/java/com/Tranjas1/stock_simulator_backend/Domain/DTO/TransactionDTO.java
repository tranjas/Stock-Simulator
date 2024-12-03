package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class TransactionDTO {
    private long id;

    private long transactionId;

    private double totalPrice;


    private String symbol;

    private double amount;
}
