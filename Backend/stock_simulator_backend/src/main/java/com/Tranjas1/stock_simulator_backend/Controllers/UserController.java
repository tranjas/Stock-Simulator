package com.Tranjas1.stock_simulator_backend.Controllers;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.UserDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import com.Tranjas1.stock_simulator_backend.Mappers.impl.UserMapperImpl;
import com.Tranjas1.stock_simulator_backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserDTO createUser(@RequestBody UserDTO user) {
        User userEntity = userMapper.mapFrom(user);
        return userMapper.mapTo(userService.createUser(userEntity));
    }
}
