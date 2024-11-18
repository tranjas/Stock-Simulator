package com.Tranjas1.stock_simulator_backend.Mappers.impl;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.PortfolioDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Portfolio;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PortfolioMapperImpl implements Mapper<Portfolio, PortfolioDTO> {
    private final ModelMapper modelMapper;

    public PortfolioMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public PortfolioDTO mapToDTO(Portfolio portfolio) {
        return modelMapper.map(portfolio, PortfolioDTO.class);
    }

    @Override
    public Portfolio mapToEntity(PortfolioDTO portfolioDTO) {
        return modelMapper.map(portfolioDTO, Portfolio.class);
    }
}
