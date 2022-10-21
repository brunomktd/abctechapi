package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AddressApplication;
import br.com.fiap.abctechapi.application.dto.AddressResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ZipCodeControllerTest {

    @InjectMocks
    private ZipCodeController controller;

    @Mock
    private AddressApplication application;

    @Test
    public void getZipCode(){
        Mockito.when(application.getAddressByZipCode(Mockito.anyString()))
                .thenReturn(AddressResponseDto.builder().zipCode("02121-020").build());

        ResponseEntity<AddressResponseDto> response = controller.searchZipCode("02121020");

        Assertions.assertEquals("02121-020", Objects.requireNonNull(response.getBody()).getZipCode());

    }

}