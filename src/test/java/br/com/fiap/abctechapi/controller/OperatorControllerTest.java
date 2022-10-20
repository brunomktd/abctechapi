package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OperatorApplication;
import br.com.fiap.abctechapi.application.dto.OperatorRequestDto;
import br.com.fiap.abctechapi.application.dto.OperatorResponseDto;
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
class OperatorControllerTest {

    @InjectMocks
    private OperatorController controller;

    @Mock
    private OperatorApplication application;

    @Test
    void createOperator() {
        Mockito.when(application.createOperator(Mockito.any()))
                .thenReturn(OperatorResponseDto.builder().operatorId(1L).build());

        ResponseEntity<OperatorResponseDto> response = controller.createOperator(Mockito.mock(OperatorRequestDto.class));

        Assertions.assertEquals(1L, Objects.requireNonNull(response.getBody()).getOperatorId());
    }

    @Test
    void getOperators() {
        Mockito.when(application.getAllOperators())
                .thenReturn(Collections.singletonList(OperatorResponseDto.builder().operatorId(1L).build()));

        ResponseEntity<List<OperatorResponseDto>> assists = controller.getOperators();

        Assertions.assertEquals(1L, Objects.requireNonNull(assists.getBody()).get(0).getOperatorId());
    }
}