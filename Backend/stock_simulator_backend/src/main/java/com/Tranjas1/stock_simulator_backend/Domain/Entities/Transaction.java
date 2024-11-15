package com.Tranjas1.stock_simulator_backend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @JoinColumn(name = "user_id")
    @ManyToOne
    User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;

    private double totalPrice;

    private String stock;

    private double amount;
}
