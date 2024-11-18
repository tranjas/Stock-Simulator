package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StockRepositoryIntegrationTest {
    private final StockRepository underTest;

    @Autowired
    public StockRepositoryIntegrationTest(StockRepository underTest) {
        this.underTest = underTest;
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

}
