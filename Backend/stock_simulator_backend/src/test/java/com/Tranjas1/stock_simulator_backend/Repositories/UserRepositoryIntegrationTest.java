package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryIntegrationTest {

    private final UserRepository underTest;

    @Autowired
    public UserRepositoryIntegrationTest(UserRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testSaveAndFindUser() {
        // Setup: Create a new user with a valid date
        LocalDate date = LocalDate.of(1990, 12, 1);
        User user = User.builder()
                .firstName("testuser")
                .lastName("last")
                .email("testuser@example.com")
                .dateOfBirth(date)
                .build();

        // Act: Save the user to the repository
        underTest.save(user);

        // Assert: Retrieve the user by ID and verify it is persisted
        Optional<User> foundUser = underTest.findById(user.getId());
        assertThat(foundUser).isPresent();  // Assert that the user is found
        assertThat(foundUser.get().getFirstName()).isEqualTo("testuser");
        assertThat(foundUser.get().getLastName()).isEqualTo("last");
        assertThat(foundUser.get().getDateOfBirth()).isEqualTo(date);
        assertThat(foundUser.get().getEmail()).isEqualTo("testuser@example.com");  // Check last name
    }
}
