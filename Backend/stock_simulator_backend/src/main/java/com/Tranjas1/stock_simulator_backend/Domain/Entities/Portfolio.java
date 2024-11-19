package com.Tranjas1.stock_simulator_backend.Domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @Column(name = "user_id")
    private long userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    @MapsId
    private User user;

    @OneToMany(mappedBy = "portfolio")
    private Set<Stock> stocks;


    private double buyingPower;
}
