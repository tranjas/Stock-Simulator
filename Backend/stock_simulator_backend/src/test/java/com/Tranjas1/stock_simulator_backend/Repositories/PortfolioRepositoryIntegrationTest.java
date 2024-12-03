package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
import com.Tranjas1.stock_simulator_backend.Services.StockService;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PortfolioRepositoryIntegrationTest {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private StockService stockService;
    @Autowired
    UserService userService;
    @Test
    @Transactional
    public void testSaveAndRetrievePortfolio() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        userService.createUser(user);

        portfolioService.createPortfolio(user.getId());

        // Step 3: Retrieve the saved Portfolio
        Portfolio retrievedPortfolio = portfolioService.getPortfolio(user.getId());

        // Step 4: Assert the Portfolio was saved and retrieved correctly
        assertThat(retrievedPortfolio).isNotNull(); // Portfolio is successfully retrieved
        assertThat(retrievedPortfolio.getUser().getFirstName()).isEqualTo("Jason"); // User data is correct
        assertThat(retrievedPortfolio.getBuyingPower()).isEqualTo(0.0); // Buying power matches
        assertThat(retrievedPortfolio.getStocks()).isEmpty(); // Stocks are initially empty
    }


    @Test
    public void testUpdatePortfolioBuyingPower() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();


        // Step 3: Create and save a Portfolio linked to the managed User
        Portfolio portfolio = Portfolio.builder()
                .userId(user.getId())
                .user(user) // Use the managedUser
                .buyingPower(1000.0)
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 3: Update the Portfolio's buying power
        portfolioService.updateBuyingPower(portfolio.getUserId(), 1000);

        // Step 4: Retrieve the updated Portfolio and verify
        Optional<Portfolio> updatedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(updatedPortfolio).isPresent();
        assertThat(updatedPortfolio.get().getBuyingPower()).isEqualTo(2000);
    }


    @Test
    public void testDeletePortfolio() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();


        // Step 3: Create and save a Portfolio linked to the managed User
        Portfolio portfolio = Portfolio.builder()
                .userId(user.getId())
                .user(user) // Use the managedUser
                .buyingPower(1000.0)
                .build();
        portfolio = portfolioRepository.save(portfolio);

       portfolioService.deletePortfolio(portfolio.getUserId());

        // Step 4: Verify the Portfolio was deleted
        Optional<Portfolio> deletedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(deletedPortfolio).isEmpty();
    }

}
