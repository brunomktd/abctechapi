package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.dto.AddressResponseDto;
import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;
import br.com.fiap.abctechapi.service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressApplicationImplTest {

    @InjectMocks
    private AddressApplicationImpl addressApplication;

    @Mock
    private AddressService service;

    @Test
    void getAddressByZipCode() {
        Mockito.when(service.searchAddressByZipCode(Mockito.anyString())).thenReturn(Mockito.mock(ViaCepResponseDto.class));

        AddressResponseDto addressByZipCode = addressApplication.getAddressByZipCode("02121020");

        Assertions.assertTrue(addressByZipCode != null);
    }
}