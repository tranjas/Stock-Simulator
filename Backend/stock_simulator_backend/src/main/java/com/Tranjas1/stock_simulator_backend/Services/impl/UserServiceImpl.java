package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.UserRepository;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
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
}
