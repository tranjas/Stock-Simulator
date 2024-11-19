package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PortfolioRepositoryIntegrationTest {

    private final PortfolioRepository portfolioRepository;

    @Autowired
    public PortfolioRepositoryIntegrationTest(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }
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
    // TODO: Fix this lazy collection problem
    public void testAddAndRemoveStockFromPortfolio() {
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
                .stocks(new HashSet<>()) // Initialize an empty set for stocks
                .build();
        portfolio = portfolioRepository.save(portfolio);

        // Step 4: Create and add a Stock to the Portfolio
        Stock stock1 = Stock.builder()
                .symbol("AAPL")
                .amount(10)
                .price(150.0)
                .portfolio(portfolio) // Link stock to portfolio
                .build();

        portfolio.getStocks().add(stock1);

        // Step 5: Assert that the stock is added
        Optional<Portfolio> updatedPortfolio = portfolioRepository.findById(user.getId());
        assertThat(updatedPortfolio).isPresent();
//        assertThat(updatedPortfolio.get().getStocks()).size().isEqualTo(1);
        assertThat(updatedPortfolio.get().getStocks().iterator().next().getSymbol()).isEqualTo("AAPL");

        // Step 6: Remove the Stock from the Portfolio
        portfolio.getStocks().remove(stock1); // Remove stock from the set

        // Step 7: Assert that the stock is removed
        Optional<Portfolio> portfolioAfterRemoval = portfolioRepository.findById(user.getId());
        assertThat(portfolioAfterRemoval).isPresent();
        assertThat(portfolioAfterRemoval.get().getStocks()).isEmpty();
    }

}
