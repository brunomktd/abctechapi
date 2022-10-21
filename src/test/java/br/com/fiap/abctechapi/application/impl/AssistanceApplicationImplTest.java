package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.dto.AssistanceRequestDto;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssistanceApplicationImplTest {

    @InjectMocks
    private AssistanceApplicationImpl application;

    @Mock
    private AssistanceService assistanceService;

    @Test
    void getAssists() {
        Mockito.when(assistanceService.getAssistanceList())
                .thenReturn(Collections.singletonList(Mockito.mock(Assistance.class)));

        List<AssistanceResponseDto> list = application.getAssists();

        Assertions.assertEquals(1, list.size());
    }

    @Test
    void createAssistance() {
        Mockito.when(assistanceService.createAssistance(Mockito.any()))
                .thenReturn(Mockito.mock(Assistance.class));

        AssistanceResponseDto assistance = application.createAssistance(Mockito.mock(AssistanceRequestDto.class));

        assertNotNull(assistance);
    }

    @Test
    void convertEntityToAssistanceResponseDto() {
        assertNotNull(application.convertEntityToAssistanceResponseDto(Mockito.mock(Assistance.class)));
    }
}