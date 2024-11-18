package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.PortfolioDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final Mapper<Portfolio, PortfolioDTO> portfolioMapper;

    public PortfolioController(PortfolioService portfolioService, Mapper<Portfolio, PortfolioDTO> portfolioMapper) {
        this.portfolioMapper = portfolioMapper;
        this.portfolioService = portfolioService;
    }
}
