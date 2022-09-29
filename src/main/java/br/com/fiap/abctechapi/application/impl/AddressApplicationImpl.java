package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AddressApplication;
import br.com.fiap.abctechapi.application.dto.AddressResponseDto;
import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;
import br.com.fiap.abctechapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressApplicationImpl implements AddressApplication {

    @Autowired
    private AddressService service;

    @Override
    public AddressResponseDto getAddressByZipCode(String zipCode) {
        ViaCepResponseDto viaCepResponseDto = service.searchAddressByZipCode(zipCode);

        return AddressResponseDto.builder()
                .zipCode(viaCepResponseDto.getCep())
                .street(viaCepResponseDto.getLogradouro())
                .uf(viaCepResponseDto.getUf())
                .neighborhood(viaCepResponseDto.getBairro())
                .city(viaCepResponseDto.getLocalidade())
                .build();
    }
}
