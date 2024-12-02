package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class StockDTO {
    String symbol;

    double amount;

    Long portfolioId;
}
