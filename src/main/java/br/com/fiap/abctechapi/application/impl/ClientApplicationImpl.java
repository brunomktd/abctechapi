package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.ClientApplication;
import br.com.fiap.abctechapi.application.dto.AddressResponseDto;
import br.com.fiap.abctechapi.application.dto.ClientRequestDto;
import br.com.fiap.abctechapi.application.dto.ClientResponseDto;
import br.com.fiap.abctechapi.model.Address;
import br.com.fiap.abctechapi.model.Client;
import br.com.fiap.abctechapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientApplicationImpl implements ClientApplication {

    @Autowired
    private ClientService clientService;

    @Override
    public ClientResponseDto create(ClientRequestDto clientRequest) {
        Client client = convertClientRequestToClient(clientRequest);
        Client entity = clientService.createClient(client);
        return convertEntityToClientResponse(entity);
    }

    private ClientResponseDto convertEntityToClientResponse(Client entity) {
        AddressResponseDto addressResponseDto = AddressResponseDto.builder()
                .id(entity.getAddress().getId())
                .street(entity.getAddress().getStreet())
                .number(entity.getAddress().getNumber())
                .neighborhood(entity.getAddress().getNeighborhood())
                .uf(entity.getAddress().getUf())
                .city(entity.getAddress().getCity())
                .zipCode(entity.getAddress().getZipCode())
                .build();

        return ClientResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .address(addressResponseDto)
                .build();
    }

    private Client convertClientRequestToClient(ClientRequestDto clientRequest) {
        Address address = Address.builder()
                .street(clientRequest.getAddress().getStreet())
                .number(clientRequest.getAddress().getNumber())
                .neighborhood(clientRequest.getAddress().getNeighborhood())
                .uf(clientRequest.getAddress().getUf())
                .city(clientRequest.getAddress().getCity())
                .zipCode(clientRequest.getAddress().getZipCode())
                .build();

        return Client.builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .address(address)
                .build();
    }
}
