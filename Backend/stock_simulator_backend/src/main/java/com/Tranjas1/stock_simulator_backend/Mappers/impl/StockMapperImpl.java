package com.Tranjas1.stock_simulator_backend.Mappers.impl;

import com.Tranjas1.stock_simulator_backend.Domain.DTO.StockDTO;
import com.Tranjas1.stock_simulator_backend.Domain.Entities.Stock;
import com.Tranjas1.stock_simulator_backend.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StockMapperImpl implements Mapper<Stock, StockDTO> {
    private final ModelMapper modelMapper;

    public StockMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public StockDTO mapToDTO(Stock stock) {
        return modelMapper.map(stock, StockDTO.class);
    }

    @Override
    public Stock mapToEntity(StockDTO stockDTO) {
        return modelMapper.map(stockDTO, Stock.class);
    }
}
