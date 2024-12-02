package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Services.PortfolioService;
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
    public void testSaveAndRetrievePortfolio() {
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
                .stocks(new HashSet<>())
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 4: Retrieve the saved Portfolio
        Optional<Portfolio> retrievedPortfolio = portfolioRepository.findById(portfolio.getUser().getId());

        // Step 5: Assert the Portfolio was saved and retrieved correctly
        assertThat(retrievedPortfolio).isPresent();
        assertThat(retrievedPortfolio.get().getUser().getFirstName()).isEqualTo("Jason");
        assertThat(retrievedPortfolio.get().getBuyingPower()).isEqualTo(1000.0);
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
                .stocks(new HashSet<>())
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 3: Update the Portfolio's buying power
        portfolio.setBuyingPower(1500.0);
        portfolioRepository.save(portfolio);

        // Step 4: Retrieve the updated Portfolio and verify
        Optional<Portfolio> updatedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(updatedPortfolio).isPresent();
        assertThat(updatedPortfolio.get().getBuyingPower()).isEqualTo(1500.0);
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
                .stocks(new HashSet<>())
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 3: Delete the Portfolio
        portfolioRepository.deleteById(user.getId());

        // Step 4: Verify the Portfolio was deleted
        Optional<Portfolio> deletedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(deletedPortfolio).isEmpty();
    }
    @Test
    @Transactional
    public void testAddStockToPortfolio() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        userService.createUser(user);
        // Step 2: Create and save a Portfolio linked to the managed User
        Portfolio portfolio = Portfolio.builder()
                .userId(user.getId())
                .user(user)
                .buyingPower(1000.0)
                .stocks(new HashSet<>())  // Ensure stocks is initialized
                .build();
        portfolio = portfolioService.createPortfolio(portfolio, user.getId());
        Optional<Portfolio> temp = portfolioRepository.findByUserId(user.getId());
        System.out.println(temp.orElse(null));
        if (temp.isPresent()) {
            System.out.println(temp.get().getUserId());
        }
        // Step 3: Create and save a Stock
        Stock stock = new Stock();
        stock.setSymbol("AAPL");
        stock.setCompany("Apple Inc.");
        stockService.createStock(stock); // Assuming you have a save method for Stock

        // Step 4: Add the stock to the portfolio
        portfolioService.updateStock(user.getId(), "AAPL", "add");
        // Step 5: Retrieve the updated Portfolio and verify the stock is added
        Optional<Portfolio> updatedPortfolio = portfolioRepository.findByUserId(user.getId());
        assertThat(updatedPortfolio).isPresent();
        assertThat(updatedPortfolio.get().getStocks()).hasSize(1);
        assertThat(updatedPortfolio.get().getStocks().stream().anyMatch(s -> s.getSymbol().equals("AAPL"))).isTrue();
    }

    @Test
    @Transactional
    public void testRemoveStockFromPortfolio() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        // Step 2: Create and save a Portfolio linked to the managed User
        Portfolio portfolio = Portfolio.builder()
                .userId(user.getId())
                .user(user)
                .buyingPower(1000.0)
                .stocks(new HashSet<>())  // Ensure stocks is initialized
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 3: Create and save a Stock
        Stock stock = new Stock();
        stock.setSymbol("AAPL");
        stock.setCompany("Apple Inc.");
        stockService.createStock(stock); // Assuming you have a save method for Stock

        // Step 4: Add the stock to the portfolio
        portfolioService.updateStock(user.getId(), "AAPL", "add");

        // Step 5: Remove the stock from the portfolio
        portfolioService.updateStock(user.getId(), "AAPL", "remove");

        // Step 6: Retrieve the updated Portfolio and verify the stock is removed
        Optional<Portfolio> updatedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(updatedPortfolio).isPresent();
        assertThat(updatedPortfolio.get().getStocks()).hasSize(0); // Ensure stock was removed
        assertThat(updatedPortfolio.get().getStocks().stream().anyMatch(s -> s.getSymbol().equals("AAPL"))).isFalse(); // Ensure stock is not in the portfolio
    }

}
