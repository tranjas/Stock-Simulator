package com.Tranjas1.stock_simulator_backend.Repositories;

import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
