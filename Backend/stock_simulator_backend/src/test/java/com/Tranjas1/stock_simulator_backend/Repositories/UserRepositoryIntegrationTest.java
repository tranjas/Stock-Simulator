package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void testSaveAndFindUser() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date dateOfBirth = dateFormat.parse("01/12/2002");
        User user = User.builder()
                .firstName("testuser")
                .lastName("last")
                .email("testuser@example.com")
                .dateOfBirth(dateOfBirth)
                .build();

        underTest.save(user);

        Optional<User> foundUser = underTest.findById(user.getId());
        assertThat(foundUser).isPresent();
        assertThat(user.getFirstName()).isEqualTo("testuser");
        assertThat(user.getLastName()).isEqualTo("last");
        assertThat(foundUser.get()).isEqualTo(user);
    }

}
