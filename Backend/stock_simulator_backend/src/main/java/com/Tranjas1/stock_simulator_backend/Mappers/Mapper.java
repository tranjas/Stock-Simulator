package com.Tranjas1.stock_simulator_backend.Mappers;

public interface Mapper <A,B>{

    B mapToDTO(A a);

    A mapToEntity(B b);
}
