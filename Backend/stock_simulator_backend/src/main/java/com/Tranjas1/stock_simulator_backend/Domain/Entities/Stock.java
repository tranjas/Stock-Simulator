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
@Table(name = "stocks")
public class Stock {
    @Id
    private String symbol;

    @JoinColumn(name = "portfolio_id")
    @ManyToOne
    private Portfolio portfolio;

    private double amount;
}
