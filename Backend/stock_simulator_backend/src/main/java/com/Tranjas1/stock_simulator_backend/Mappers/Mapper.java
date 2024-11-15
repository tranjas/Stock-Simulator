package com.Tranjas1.stock_simulator_backend.Mappers;

public interface Mapper <A,B>{

    B mapTo(A a);

    A mapFrom(B b);
}
