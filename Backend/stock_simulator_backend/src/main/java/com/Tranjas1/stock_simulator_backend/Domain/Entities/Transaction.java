package com.Tranjas1.stock_simulator_backend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "transactions")
public class Transaction {
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "stock_symbol")
    private Stock stock;

    private double amount;
}
