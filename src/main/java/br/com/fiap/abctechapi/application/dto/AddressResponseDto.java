package br.com.fiap.abctechapi.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressResponseDto {
    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String uf;
    private String city;
    private String neighborhood;
}
