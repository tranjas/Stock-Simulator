package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class PortfolioDTO {
    private long portfolioId;

    private long id;

    private Set<Stock> stocks;

    private double totalAmount;

    private double buyingPower;
}
