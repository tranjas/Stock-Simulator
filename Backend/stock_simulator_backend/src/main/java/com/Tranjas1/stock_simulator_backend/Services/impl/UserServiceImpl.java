package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.UserRepository;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        if (user.getFirstName() == null) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (user.getLastName() == null) {
            throw new IllegalArgumentException("Last Name is Required");
        }
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email is Required");
        }
        if (user.getDateOfBirth() == null) {
            throw new IllegalArgumentException("Date of BIrth is  Required");
        }
        return userRepository.save(user);
    }
    @Override
    public boolean deleteUser(long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUser(long id) {
        Optional<User> currentUser = userRepository.findById(id);
        return currentUser.orElse(null);
    }
    @Override
    public User updateUser(long id, User user) {
        Optional<User> currentUser = userRepository.findById(id);
        if (currentUser.isPresent()) {
            User newUser = currentUser.get();
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setEmail(user.getEmail());
            return userRepository.save(newUser);
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found.");
        }
    }
}
