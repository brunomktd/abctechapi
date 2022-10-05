package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AddressResponseDto;

public interface AddressApplication {
    AddressResponseDto getAddressByZipCode(String zipCode);
}
