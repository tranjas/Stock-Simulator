package com.Tranjas1.stock_simulator_backend.Domain.DTO;

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

    private Map<String, Double> stocks;

    private double buyingPower;
}
