package com.Tranjas1.stock_simulator_backend.Services;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;

public interface UserService {

    User createUser(User user);
    boolean deleteUser(long id);

    User updateUser(long id, User user);
}
