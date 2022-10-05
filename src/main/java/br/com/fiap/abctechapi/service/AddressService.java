package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;

public interface AddressService {
    ViaCepResponseDto searchAddressByZipCode(String zipCode);
}
