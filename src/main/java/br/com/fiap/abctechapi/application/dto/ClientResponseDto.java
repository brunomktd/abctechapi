package br.com.fiap.abctechapi.application.dto;


import br.com.fiap.abctechapi.model.Address;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class ClientResponseDto {
    private Long id;
    private String name;
    private String email;
    private AddressResponseDto address;
}
