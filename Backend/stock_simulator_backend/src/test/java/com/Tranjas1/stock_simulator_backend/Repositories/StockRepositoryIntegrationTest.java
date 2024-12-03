package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.PortfolioDTO;
import com.Tranjas1.stock_simulator_backend.Domain.DTO.StockDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
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

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockRepositoryIntegrationTest {
    private final StockService stockService;
    private final PortfolioService portfolioService;
    private final UserService userService;
    private final Mapper<Stock, StockDTO> stockMapper;
    private final Mapper<Portfolio, PortfolioDTO> portfolioMapper;

    @Autowired
    public StockRepositoryIntegrationTest(StockService stockService, PortfolioService portfolioService, UserService userService,
                                          Mapper<Stock, StockDTO> stockMapper, Mapper<Portfolio, PortfolioDTO> portfolioMapper) {
        this.stockService = stockService;
        this.portfolioService = portfolioService;
        this.userService = userService;
        this.stockMapper = stockMapper;
        this.portfolioMapper = portfolioMapper;
    }

    @Test
    @Transactional
    public void testAddStock() {
        // Step 1: Create and save a User
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        userService.createUser(user);

        // Step 2: Create a Portfolio for the User
        PortfolioDTO portfolio = portfolioMapper.mapToDTO(portfolioService.createPortfolio(user.getId()));

        // Step 3: Add Stocks to Portfolio
        StockDTO stock = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "APPL", 10));
        StockDTO stock2 = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "NVIDIA", 11));

        // Step 4: Retrieve the Portfolio and verify Stock counts and details
        PortfolioDTO retrievedPortfolio = portfolioMapper.mapToDTO(portfolioService.getPortfolio(user.getId()));

        System.out.println("Portfolio : " + retrievedPortfolio);

        assertThat(stock.getSymbol()).isEqualTo("APPL");
        assertThat(stock.getAmount()).isEqualTo(10.0);
        assertThat(retrievedPortfolio.getStocks().size()).isEqualTo(2);
    }

    @Test
    @Transactional
    public void testRemoveStock() {
        // Step 1: Create and save a User and Portfolio
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        userService.createUser(user);
        PortfolioDTO portfolio = portfolioMapper.mapToDTO(portfolioService.createPortfolio(user.getId()));

        // Step 2: Add a Stock to Portfolio
        StockDTO stock = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "APPL", 10));

        // Step 3: Remove Stock from Portfolio
        stockService.deleteStock(portfolio.getId(), stock.getSymbol());

        // Step 4: Retrieve the Portfolio and verify Stock count
        PortfolioDTO retrievedPortfolio = portfolioMapper.mapToDTO(portfolioService.getPortfolio(user.getId()));
        assertThat(retrievedPortfolio.getStocks()).isEmpty();
    }
//
    @Test
    @Transactional
    public void testUpdateStock() {
        // Step 1: Create and save a User and Portfolio
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        userService.createUser(user);
        PortfolioDTO portfolio = portfolioMapper.mapToDTO(portfolioService.createPortfolio(user.getId()));

        // Step 2: Add a Stock to Portfolio
        Stock stock = stockService.createStock(portfolio.getId(), "APPL", 10);

        stockService.updateStock(portfolio.getId(), stock.getSymbol(), 15);

        // Step 4: Retrieve the updated Stock and verify its details
        StockDTO updatedStock = stockMapper.mapToDTO(stockService.getStock(portfolio.getId(), "APPL"));
        assertThat(updatedStock.getAmount()).isEqualTo(25.0);
    }

    @Test
    @Transactional
    public void testFindStockBySymbol() {
        // Step 1: Create and save a User and Portfolio
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        userService.createUser(user);
        PortfolioDTO portfolio = portfolioMapper.mapToDTO(portfolioService.createPortfolio(user.getId()));

        // Step 2: Add multiple Stocks to Portfolio
        StockDTO stock1 = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "APPL", 10));
        StockDTO stock2 = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "GOOG", 5));

        // Step 3: Retrieve Stock by Symbol and verify its details
        StockDTO result = stockMapper.mapToDTO(stockService.getStock(portfolio.getId(), "APPL"));
        assertThat(result.getSymbol()).isEqualTo("APPL");
        assertThat(result.getAmount()).isEqualTo(10.0);

        result = stockMapper.mapToDTO(stockService.getStock(portfolio.getId(), "GOOG"));
        assertThat(result.getSymbol()).isEqualTo("GOOG");
        assertThat(result.getAmount()).isEqualTo(5.0);
    }

    @Test
    @Transactional
    public void testAddStockWithSameSymbol() {
        // Step 1: Create and save a User and Portfolio
        User user = User.builder()
                .firstName("Jason")
                .lastName("Tran")
                .email("jason.tran@example.com")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
        userService.createUser(user);
        PortfolioDTO portfolio = portfolioMapper.mapToDTO(portfolioService.createPortfolio(user.getId()));

        // Step 2: Add a Stock to Portfolio
        StockDTO stock = stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "APPL", 10));

        // Step 3: Attempt to add another Stock with the same symbol
        // Here we expect an exception
        Exception exception = null;
        try {
            stockMapper.mapToDTO(stockService.createStock(portfolio.getId(), "APPL", 20));
        } catch (Exception e) {
            exception = e; // Catch the exception
        }

        // Step 4: Assert that the exception was thrown and it contains the expected message
        assertThat(exception).isNotNull();
        assertThat(exception.getMessage()).contains("Stock with symbol APPL already exists in the portfolio");
    }


}
