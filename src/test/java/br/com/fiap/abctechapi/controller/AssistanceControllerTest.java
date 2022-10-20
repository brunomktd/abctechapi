package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceRequestDto;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
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
class AssistanceControllerTest {

    @InjectMocks
    private AssistanceController assistanceController;

    @Mock
    private AssistanceApplication assistanceApplication;

    @Test
    public void shouldCreateAssist() {

        Mockito.when(assistanceApplication.createAssistance(Mockito.any()))
                .thenReturn(AssistanceResponseDto.builder().id(1L).build());

        AssistanceRequestDto request = new AssistanceRequestDto();
        request.setName("Assistência Técnica");
        request.setDescription("Manutenção de linha branca");

        ResponseEntity<AssistanceResponseDto> response = assistanceController.createAssist(request);

        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    public void getListAssists() {
        Mockito.when(assistanceApplication.getAssists())
                .thenReturn(Collections.singletonList(AssistanceResponseDto.builder().id(1L).build()));

        ResponseEntity<List<AssistanceResponseDto>> assists = assistanceController.getAssists();

        Assertions.assertEquals(1L, Objects.requireNonNull(assists.getBody()).get(0).getId());
    }

}