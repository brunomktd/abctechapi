package br.com.fiap.abctechapi.application.dto;

import lombok.Data;
@Data
public class ClientRequestDto {
    private String name;
    private String email;
    private AddressRequestDto address;
}
