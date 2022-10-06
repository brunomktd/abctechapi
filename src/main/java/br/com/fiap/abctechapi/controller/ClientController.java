package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.ClientApplication;
import br.com.fiap.abctechapi.application.dto.ClientRequestDto;
import br.com.fiap.abctechapi.application.dto.ClientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientApplication clientApplication;

    @PostMapping
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequestDto clientRequest){
        ClientResponseDto clientResponseDto = clientApplication.create(clientRequest);
        URI uri = UriComponentsBuilder.fromPath("/clients/{id}").buildAndExpand(clientResponseDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        List<ClientResponseDto> clients = clientApplication.getAllClients();
        return ResponseEntity.ok(clients);
    }
}
