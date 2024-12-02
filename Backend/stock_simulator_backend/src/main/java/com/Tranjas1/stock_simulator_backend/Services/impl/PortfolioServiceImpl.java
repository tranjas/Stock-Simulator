package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.PortfolioRepository;
import com.Tranjas1.stock_simulator_backend.Repositories.UserRepository;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;
    @Autowired
    public PortfolioServiceImpl(PortfolioRepository portfolioRepository, UserRepository userRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Portfolio createPortfolio(Portfolio portfolioEntity, long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        portfolioEntity.setUser(user); // Set user
        portfolioEntity.setUserIdwithUser(user);

        return portfolioRepository.save(portfolioEntity);
    }

    @Override
    public boolean deletePortfolio(long user_id) {
        Optional<Portfolio> userOptional = portfolioRepository.findById(user_id);
        if (userOptional.isPresent()) {
            portfolioRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateBuyingPower(long user_id, double amount) {
        Portfolio portfolio = portfolioRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found for user ID: " + user_id));
        if (portfolio.getBuyingPower() + amount < 0.0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Buying power will be negative");
        }
        portfolio.setBuyingPower(portfolio.getBuyingPower() + amount);
        portfolioRepository.save(portfolio);
    }

    @Override
    public Portfolio getPortfolio(long user_id) {
        return  portfolioRepository.findById(user_id)
                .orElseThrow(() -> new EntityNotFoundException("Portfolio not found for user ID: " + user_id));
    }


}
