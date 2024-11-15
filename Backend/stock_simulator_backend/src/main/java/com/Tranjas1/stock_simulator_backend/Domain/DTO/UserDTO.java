package com.Tranjas1.stock_simulator_backend.Domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor // for jackson to convert to JSON or from
@Builder
public class UserDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
}
