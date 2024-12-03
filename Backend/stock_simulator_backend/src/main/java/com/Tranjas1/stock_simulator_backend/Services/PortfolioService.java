package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import org.springframework.transaction.annotation.Transactional;

public interface PortfolioService {

    Portfolio createPortfolio(long userId);

    boolean deletePortfolio(long user_id);

    void updateBuyingPower(long user_id, double amount);

    Portfolio getPortfolio(long user_id);

}
