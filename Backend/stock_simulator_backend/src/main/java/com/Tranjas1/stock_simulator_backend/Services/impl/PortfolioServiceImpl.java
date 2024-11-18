package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Repositories.PortfolioRepository;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }
}
