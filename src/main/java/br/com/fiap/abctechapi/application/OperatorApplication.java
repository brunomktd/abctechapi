package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OperatorRequestDto;
import br.com.fiap.abctechapi.application.dto.OperatorResponseDto;

import java.util.List;

public interface OperatorApplication {
    OperatorResponseDto createOperator(OperatorRequestDto operatorRequestDto);

    List<OperatorResponseDto> getAllOperators();
}
