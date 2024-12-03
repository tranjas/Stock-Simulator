package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.PortfolioDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Transaction;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import com.Tranjas1.stock_simulator_backend.Services.TransactionService;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionRepositoryIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    PortfolioService portfolioService;

    @Autowired
    StockService stockService;

    @Test
    @Transactional
    public void BuyStockWithTransaction() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        userService.createUser(user);

        // Step 2: Create a Portfolio for the User and the buying power
        Portfolio portfolio = portfolioService.createPortfolio(user.getId());
        portfolioService.updateBuyingPower(portfolio.getUserId(), 1000);
        System.out.println(portfolio);

        Transaction transaction = transactionService.createTransaction(user.getId(), "APPL", "Buy", 10, 100);

        Transaction transaction2 = transactionService.createTransaction(user.getId(), "TSLA", "Buy", 15, 500);
        System.out.println(transaction);
        System.out.println(portfolio);

        assertThat(portfolio.getBuyingPower()).isEqualTo(400);

    }

    @Test
    @Transactional
    public void SellStockWithTransaction() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        userService.createUser(user);

        // Step 2: Create a Portfolio for the User and the buying power
        Portfolio portfolio = portfolioService.createPortfolio(user.getId());
        portfolioService.updateBuyingPower(portfolio.getUserId(), 1000);
        System.out.println(portfolio);

        // Step 3: Create a Buy transaction to add stock to the portfolio
        Transaction buyTransaction = transactionService.createTransaction(user.getId(), "APPL", "Buy", 10, 100);
        System.out.println("Buy Transaction: " + buyTransaction);

        // Step 4: Sell stock with "Sell" transaction
        Transaction sellTransaction = transactionService.createTransaction(user.getId(), "APPL", "Sell", 5, 120);
        System.out.println("Sell Transaction: " + sellTransaction);

        System.out.println(portfolio);
        // Step 5: Assert the portfolio's buying power and stock balance
        assertThat(portfolio.getBuyingPower()).isEqualTo(1020); // Buying power should increase after selling
        // You can also assert the stock balance in the portfolio (if applicable, depending on how stock tracking is implemented)
        // e.g., assertThat(portfolio.getStockAmount("APPL")).isEqualTo(5);
    }

}
