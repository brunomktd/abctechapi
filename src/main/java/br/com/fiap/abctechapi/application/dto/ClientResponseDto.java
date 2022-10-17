package br.com.fiap.abctechapi.application.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponseDto {
    private Long id;
    private String name;
    private String email;
    private AddressResponseDto address;
}
