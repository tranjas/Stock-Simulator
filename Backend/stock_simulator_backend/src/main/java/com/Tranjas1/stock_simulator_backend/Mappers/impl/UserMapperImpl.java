package com.Tranjas1.stock_simulator_backend.Mappers.impl;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.UserDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.User;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl  implements Mapper<User, UserDTO> {
    private final ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO mapToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User mapToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
