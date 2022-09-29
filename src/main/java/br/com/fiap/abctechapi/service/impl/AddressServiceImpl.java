package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;
import br.com.fiap.abctechapi.integrations.ViaCepClient;
import br.com.fiap.abctechapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public ViaCepResponseDto searchAddressByZipCode(String zipCode) {
        return viaCepClient.searchZipCode(zipCode);
    }
}
