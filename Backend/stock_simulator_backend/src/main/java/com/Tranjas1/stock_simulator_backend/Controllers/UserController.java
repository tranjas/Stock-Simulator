package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.UserDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Mappers.impl.UserMapperImpl;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;
    private Mapper<User, UserDTO> userMapper;

    public UserController(UserService userService, Mapper<User, UserDTO> userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }
    @PostMapping(path = "/createUser")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        User userEntity = userMapper.mapFrom(user);
        User userSaved = userService.createUser(userEntity);
        return new ResponseEntity<>(userMapper.mapTo(userSaved), HttpStatus.CREATED);
    }
}
