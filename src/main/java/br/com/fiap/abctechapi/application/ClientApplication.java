package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.ClientRequestDto;
import br.com.fiap.abctechapi.application.dto.ClientResponseDto;

public interface ClientApplication {
    ClientResponseDto create(ClientRequestDto clientRequest);
}
