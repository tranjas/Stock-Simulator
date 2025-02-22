package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class PortfolioDTO {
    private long portfolioId;

    private long id;

    private Set<Stock> stocks;

    private double buyingPower;
}
