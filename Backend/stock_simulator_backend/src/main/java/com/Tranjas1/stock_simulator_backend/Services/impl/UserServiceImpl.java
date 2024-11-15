package com.Tranjas1.stock_simulator_backend.Services.impl;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Repositories.UserRepository;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
