package com.Tranjas1.stock_simulator_backend.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "portfolios")
public class Portfolio {
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    private double amount;

    private Map<String, Integer> stockTracker;
}
