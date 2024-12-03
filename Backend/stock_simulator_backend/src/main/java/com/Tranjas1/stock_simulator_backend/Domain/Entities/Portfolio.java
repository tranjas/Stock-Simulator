package com.Tranjas1.stock_simulator_backend.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @JoinColumn(name = "user_id")
    private long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    // Link the portfolio to a set of stocks
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Prevent recursion when serializing
    private Set<Stock> stocks = new HashSet<>();

    private double buyingPower;

    public void addStock(Stock stock) {
        if (this.stocks.contains(stock)) return;  // Avoid duplicates
        this.stocks.add(stock);
        stock.setPortfolio(this);  // Link the stock to this portfolio
    }


    public void removeStock(Stock stock) {
        if (!this.stocks.contains(stock)) return;
        this.stocks.remove(stock);
        stock.setPortfolio(null);  // Remove the stock's portfolio
    }

    // Optionally, override toString() to print stock symbols without recursion
    @Override
    public String toString() {
        StringBuilder stockDetails = new StringBuilder();

        // Loop through each stock and append relevant details (not the entire object)
        for (Stock stock : stocks) {
            stockDetails.append("Stock{symbol='").append(stock.getSymbol())
                    .append("', amount=").append(stock.getAmount()).append("}, ");
        }

        // Remove the trailing comma and space if there are stocks
        if (stockDetails.length() > 0) {
            stockDetails.setLength(stockDetails.length() - 2);
        }

        // Return a formatted string with portfolio details and stock information
        return "Portfolio{" +
                "userId=" + userId +
                ", buyingPower=" + buyingPower +
                ", user=" + (user != null ? user.getId() : null) +  // Avoid recursion here
                ", stocks=[" + stockDetails.toString() + "]" +  // Print stock details without recursion
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, buyingPower);  // Only hash relevant fields
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return userId == portfolio.userId && Double.compare(portfolio.buyingPower, buyingPower) == 0;
    }


}


