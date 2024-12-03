package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
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

    private final UserService userService;

    @Autowired
    public UserRepositoryIntegrationTest(UserRepository underTest, UserService userService) {
        this.underTest = underTest;
        this.userService = userService;
    }

    public User setUser(String firstName, String lastName, String email, LocalDate date) {
        return User.builder().firstName(firstName).lastName(lastName).email(email).dateOfBirth(date).build();
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
        userService.createUser(user);

        // Assert: Retrieve the user by ID and verify it is persisted
        Optional<User> foundUser = underTest.findById(user.getId());
        assertThat(foundUser).isPresent();  // Assert that the user is found
        assertThat(foundUser.get().getFirstName()).isEqualTo("testuser");
        assertThat(foundUser.get().getLastName()).isEqualTo("last");
        assertThat(foundUser.get().getDateOfBirth()).isEqualTo(date);
        assertThat(foundUser.get().getEmail()).isEqualTo("testuser@example.com");  // Check last name
    }

    @Test
    public void testUserDelete() {
        User temp = setUser("Jason", "Tran", "test@email.com", LocalDate.of(2002,06,12));
        underTest.save(temp);
        Optional<User> users = underTest.findById(temp.getId());
        assertThat(users).isPresent();
        userService.deleteUser(temp.getId());
        users = underTest.findById(temp.getId());
        assertThat(users).isEmpty();
    }

    @Test
    public void testUserUpdate() {
        User temp = setUser("Jason", "Tran", "test@email.com", LocalDate.of(2002,06,12));
        underTest.save(temp);
        temp.setFirstName("Joe");
        temp.setLastName("Doe");
        userService.updateUser(temp.getId(), temp);
        assertThat(temp.getFirstName()).isEqualTo("Joe");
        assertThat(temp.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testEmptyDatabase() {
        Optional<User> user = underTest.findById(999L);
        assertThat(user).isEmpty();
    }

    @Test
    public void testSaveMultipleUsers() {
        User user1 = setUser("User1", "Test", "user1@example.com", LocalDate.of(1995, 1, 1));
        User user2 = setUser("User2", "Test", "user2@example.com", LocalDate.of(1996, 2, 2));
        underTest.save(user1);
        underTest.save(user2);

        assertThat(underTest.findAll()).contains(user1, user2);
    }

    @Test
    public void testConcurrency() throws InterruptedException {
        User user = setUser("Concurrent", "User", "concurrent@example.com", LocalDate.of(1990, 12, 1));
        underTest.save(user);

        Runnable task = () -> {
            user.setFirstName("Updated Concurrent");
            underTest.save(user);
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Optional<User> updatedUser = underTest.findById(user.getId());
        assertThat(updatedUser.get().getFirstName()).isEqualTo("Updated Concurrent");
    }


}
