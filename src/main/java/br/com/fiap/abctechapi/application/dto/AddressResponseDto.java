package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponseDto {
    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String uf;
    private String city;
    private String neighborhood;
}
