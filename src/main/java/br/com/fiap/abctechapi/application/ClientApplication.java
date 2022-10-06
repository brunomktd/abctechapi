package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.ClientRequestDto;
import br.com.fiap.abctechapi.application.dto.ClientResponseDto;

import java.util.List;

public interface ClientApplication {
    ClientResponseDto create(ClientRequestDto clientRequest);

    List<ClientResponseDto> getAllClients();
}
