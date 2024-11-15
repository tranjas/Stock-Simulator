package com.Tranjas1.stock_simulator_backend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "stocks")
public class Stock {

    @Id
    private String symbol;

    private double amount;

    private double price;

    private String company;

    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
}
