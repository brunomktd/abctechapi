package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.application.dto.ViaCepResponseDto;
import br.com.fiap.abctechapi.integrations.ViaCepClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl service;

    @Mock
    private ViaCepClient viaCepClient;

    @Test
    public void shouldGetInstanceAddressWhenMethodCalled() {
        Mockito.when(viaCepClient.searchZipCode(Mockito.anyString())).thenReturn(Mockito.mock(ViaCepResponseDto.class));
        ViaCepResponseDto viaCepResponseDto = service.searchAddressByZipCode("02121020");
        Assertions.assertNotNull(viaCepResponseDto);
    }

}