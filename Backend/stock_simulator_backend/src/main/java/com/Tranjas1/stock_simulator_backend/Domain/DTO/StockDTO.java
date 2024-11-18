package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class StockDTO {
    private String symbol;

    private double amount;

    private double price;

    private String company;

    private LocalDate lastUpdated;

    private Portfolio portfolio;
}
