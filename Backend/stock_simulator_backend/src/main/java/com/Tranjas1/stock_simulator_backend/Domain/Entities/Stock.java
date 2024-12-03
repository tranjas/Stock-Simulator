package com.Tranjas1.stock_simulator_backend.Domain.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "stocks", uniqueConstraints = @UniqueConstraint(columnNames = {"portfolio_id", "symbol"}))
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    // Use @ManyToOne to link the stock to the portfolio.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    @JsonBackReference  // Prevent recursion when serializing
    private Portfolio portfolio;

    private double amount;

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", amount=" + amount +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(symbol, amount);  // Only hash relevant fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Double.compare(stock.amount, amount) == 0 && Objects.equals(symbol, stock.symbol);
    }

}


