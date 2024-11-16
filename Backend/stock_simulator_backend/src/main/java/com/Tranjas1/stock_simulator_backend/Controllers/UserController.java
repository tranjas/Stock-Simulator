package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.UserDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;
    private Mapper<User, UserDTO> userMapper;

    @Autowired
    public UserController(UserService userService, Mapper<User, UserDTO> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping(path = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        User userEntity = userMapper.mapToEntity(user);
        User userSaved = userService.createUser(userEntity);
        return new ResponseEntity<>(userMapper.mapToDTO(userSaved), HttpStatus.CREATED); // 201 if user was created successfully
    }

    @DeleteMapping(path= "/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build(); // 204 if delete was successful else 404 if not found
    }


}
