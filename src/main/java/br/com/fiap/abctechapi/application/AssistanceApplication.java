package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistanceRequestDto;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
import br.com.fiap.abctechapi.model.Assistance;

import java.util.List;

public interface AssistanceApplication {
    List<AssistanceResponseDto> getAssists();

    AssistanceResponseDto createAssistance(AssistanceRequestDto assistanceRequestDto);

    AssistanceResponseDto convertEntityToAssistanceResponseDto(Assistance entity);

}
