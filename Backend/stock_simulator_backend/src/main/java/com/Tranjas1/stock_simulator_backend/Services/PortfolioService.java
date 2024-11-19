package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;

public interface PortfolioService {
    Portfolio createPortfolio(Portfolio portfolioEntity);

    boolean deletePortfolio(long user_id);

    void updateBuyingPower(long user_id, double amount);

    Portfolio getPortfolio(long user_id);

    Portfolio updateStock(long user_id, String symbol, String method);
}
