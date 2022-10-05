package br.com.fiap.abctechapi.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ClientRequestDto {
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotNull(message = "email is mandatory")
    @NotEmpty(message = "email can't be empty")
    private String email;
    @NotNull(message = "address is mandatory")
    private AddressRequestDto address;
}
