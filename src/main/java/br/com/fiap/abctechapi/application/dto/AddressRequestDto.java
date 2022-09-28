package br.com.fiap.abctechapi.application.dto;

import lombok.Data;

@Data
public class AddressRequestDto {
    private String street;
    private String number;
    private String zipCode;
    private String uf;
    private String city;
    private String neighborhood;
}
