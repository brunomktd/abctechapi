package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.OperatorApplication;
import br.com.fiap.abctechapi.application.dto.OperatorRequestDto;
import br.com.fiap.abctechapi.application.dto.OperatorResponseDto;
import br.com.fiap.abctechapi.model.Operator;
import br.com.fiap.abctechapi.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OperatorApplicationImpl implements OperatorApplication {

    @Autowired
    private OperatorService service;

    @Override
    public OperatorResponseDto createOperator(OperatorRequestDto operatorRequestDto) {
        Operator operator = Operator.builder()
                .name(operatorRequestDto.getName())
                .build();

        Operator entity = service.saveOperator(operator);

        return OperatorResponseDto.builder()
                .operatorId(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public List<OperatorResponseDto> getAllOperators() {
        List<Operator> operatorsList = service.getAllOperators();
        List<OperatorResponseDto> operatorResponseDtoList = new ArrayList<>();
        if (!operatorsList.isEmpty() ) {
            operatorResponseDtoList = operatorsList.stream().map(operator ->
                    OperatorResponseDto.builder()
                            .operatorId(operator.getId())
                            .name(operator.getName())
                            .build()
            ).collect(Collectors.toList());
        }
        return operatorResponseDtoList;
    }
}
