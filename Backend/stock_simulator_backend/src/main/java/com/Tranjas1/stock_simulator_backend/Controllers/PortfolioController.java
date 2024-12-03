package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.PortfolioDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PortfolioController {
    private final PortfolioService portfolioService;
    private final Mapper<Portfolio, PortfolioDTO> portfolioMapper;

    @Autowired
    public PortfolioController(PortfolioService portfolioService, Mapper<Portfolio, PortfolioDTO> portfolioMapper) {
        this.portfolioMapper = portfolioMapper;
        this.portfolioService = portfolioService;
    }

    @PostMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDTO> createPortfolio (@PathVariable long id) {
        Portfolio portfolioSaved = portfolioService.createPortfolio(id);
        return new ResponseEntity<>(portfolioMapper.mapToDTO(portfolioSaved), HttpStatus.CREATED);
    }

    @GetMapping(path = "/portfolios/{id}")
    public ResponseEntity<PortfolioDTO> getPortfolio(@PathVariable long id) {
        Portfolio portfolio = portfolioService.getPortfolio(id);
        return new ResponseEntity<>(portfolioMapper.mapToDTO(portfolio), HttpStatus.OK);
    }

    @DeleteMapping(path = "/portfolios/{id}")
    public ResponseEntity<Void> deletePortfolio (@PathVariable long id) {
        boolean isDeleted = portfolioService.deletePortfolio(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/portfolios/{id}/{amount}")
    public ResponseEntity<Void> updateBuyingPower (@PathVariable long id, @PathVariable double amount) {
        portfolioService.updateBuyingPower(id, amount);
        return ResponseEntity.noContent().build();
    }

}
