package br.com.fiap.abctechapi.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddressRequestDto {
    @NotNull(message = "street is mandatory")
    @NotEmpty(message = "street can't be empty")
    private String street;
    @NotNull(message = "number is mandatory")
    @NotEmpty(message = "number can't be empty")
    private String number;
    @NotNull(message = "zipCode is mandatory")
    @NotEmpty(message = "zipCode can't be empty")
    private String zipCode;
    @NotNull(message = "uf is mandatory")
    @NotEmpty(message = "uf can't be empty")
    private String uf;
    @NotNull(message = "city is mandatory")
    @NotEmpty(message = "city can't be empty")
    private String city;
    @NotNull(message = "neighborhood is mandatory")
    @NotEmpty(message = "neighborhood can't be empty")
    private String neighborhood;
}
