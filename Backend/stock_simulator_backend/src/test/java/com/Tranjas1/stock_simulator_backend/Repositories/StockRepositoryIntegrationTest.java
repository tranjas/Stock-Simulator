package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockRepositoryIntegrationTest {
    private final StockRepository underTest;
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public StockRepositoryIntegrationTest(StockRepository underTest, PortfolioRepository portfolioRepository) {
        this.underTest = underTest;
        this.portfolioRepository = portfolioRepository;
    }

    @Test
    public void testAddStock() {
        LocalDate date = LocalDate.of(1990, 12, 1);
        Stock stock = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(date).build();
        underTest.save(stock);
        Optional<Stock> result = underTest.findById(stock.getSymbol());
        assertThat(result).isPresent();
        assertThat(result.get().getSymbol()).isEqualTo("APPL");
        assertThat(result.get().getAmount()).isEqualTo(10.0);
    }

    @Test
    public void testRemoveStock() {
        LocalDate date = LocalDate.of(1990, 12, 1);
        Stock stock = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(date).build();
        underTest.save(stock);
        Optional<Stock> result = underTest.findById(stock.getSymbol());
        assertThat(result).isPresent();
        underTest.delete(stock);
        result = underTest.findById(stock.getSymbol());
        assertThat(result).isEmpty();

    }
    @Test
    public void testUpdateStock() {
        LocalDate date = LocalDate.of(1990, 12, 1);
        Stock stock = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(date).build();
        underTest.save(stock);

        // Update stock
        stock.setPrice(180.0);
        stock.setAmount(15.0);
        underTest.save(stock);

        // Retrieve and assert updated values
        Optional<Stock> result = underTest.findById(stock.getSymbol());
        assertThat(result).isPresent();
        assertThat(result.get().getPrice()).isEqualTo(180.0);
        assertThat(result.get().getAmount()).isEqualTo(15.0);
    }

    @Test
    public void testFindStockBySymbol() {
        LocalDate date = LocalDate.of(1990, 12, 1);
        Stock stock1 = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(date).build();
        Stock stock2 = Stock.builder().amount(5.0).company("Google").symbol("GOOG").price(2800.0).lastUpdated(date).build();

        underTest.save(stock1);
        underTest.save(stock2);

        Optional<Stock> result = underTest.findById("APPL");
        assertThat(result).isPresent();
        assertThat(result.get().getSymbol()).isEqualTo("APPL");

        result = underTest.findById("GOOG");
        assertThat(result).isPresent();
        assertThat(result.get().getSymbol()).isEqualTo("GOOG");
    }

    @Test
    public void testAddStockWithSameSymbol() {
        Stock stock = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(LocalDate.now()).build();
        underTest.save(stock);

        // Adding another stock with the same symbol (should update)
        Stock stockUpdated = Stock.builder().amount(20.0).company("Apple").symbol("APPL").price(175.0).lastUpdated(LocalDate.now()).build();
        underTest.save(stockUpdated);

        // Retrieve and assert updated values
        Optional<Stock> result = underTest.findById("APPL");
        assertThat(result).isPresent();
        assertThat(result.get().getAmount()).isEqualTo(20.0);
        assertThat(result.get().getPrice()).isEqualTo(175.0);
    }

    @Test
    public void testStockTotalValue() {
        Stock stock = Stock.builder().amount(10.0).company("Apple").symbol("APPL").price(169.0).lastUpdated(LocalDate.now()).build();
        underTest.save(stock);

        double totalValue = stock.getAmount() * stock.getPrice();
        assertThat(totalValue).isEqualTo(1690.0);
    }
}
