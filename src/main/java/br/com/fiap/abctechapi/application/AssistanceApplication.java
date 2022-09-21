package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistanceDto;

import java.util.List;

public interface AssistanceApplication {
    public List<AssistanceDto> getAssists();
}
