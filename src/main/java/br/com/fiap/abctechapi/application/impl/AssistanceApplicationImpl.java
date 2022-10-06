package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceRequestDto;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    @Autowired
    private AssistanceService assistanceService;

    @Override
    public List<AssistanceResponseDto> getAssists() {
        List<Assistance> assistanceList = assistanceService.getAssistanceList();
        return assistanceList.stream()
                .map(this::convertEntityToAssistanceResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AssistanceResponseDto createAssistance(AssistanceRequestDto assistanceRequestDto) {
        Assistance assistance = Assistance.builder()
                .name(assistanceRequestDto.getName())
                .description(assistanceRequestDto.getDescription())
                .build();

        Assistance entity = assistanceService.createAssistance(assistance);

        return this.convertEntityToAssistanceResponseDto(entity);
    }

    @Override
    public AssistanceResponseDto convertEntityToAssistanceResponseDto(Assistance entity) {
        return AssistanceResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
