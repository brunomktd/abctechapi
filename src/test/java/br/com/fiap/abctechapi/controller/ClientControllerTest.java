package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.ClientApplication;
import br.com.fiap.abctechapi.application.dto.ClientRequestDto;
import br.com.fiap.abctechapi.application.dto.ClientResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @InjectMocks
    private ClientController controller;

    @Mock
    private ClientApplication application;

    @Test
    void create() {
        Mockito.when(application.create(Mockito.any()))
                .thenReturn(ClientResponseDto.builder().id(1L).build());

        ResponseEntity<ClientResponseDto> response = controller.create(Mockito.mock(ClientRequestDto.class));

        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void getAllClients() {
        Mockito.when(application.getAllClients())
                .thenReturn(Collections.singletonList(ClientResponseDto.builder().id(1L).build()));

        ResponseEntity<List<ClientResponseDto>> assists = controller.getAllClients();

        Assertions.assertEquals(1L, Objects.requireNonNull(assists.getBody()).get(0).getId());
    }
}