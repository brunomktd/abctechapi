package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistanceRequestDto;
import br.com.fiap.abctechapi.application.dto.AssistanceResponseDto;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/assistances")
public class AssistanceController {

    @Autowired
    private AssistanceApplication assistanceApplication;

    @PostMapping
    public ResponseEntity<AssistanceResponseDto> createAssist(@Valid @RequestBody AssistanceRequestDto assistanceRequestDto) {
        AssistanceResponseDto assistance = this.assistanceApplication.createAssistance(assistanceRequestDto);
        URI uri = UriComponentsBuilder.fromPath("/assistances/{id}").buildAndExpand(assistance.getId()).toUri();
        return ResponseEntity.created(uri).body(assistance);
    }


    @GetMapping()
    public ResponseEntity<List<AssistanceResponseDto>> getAssists(){
        List<AssistanceResponseDto> assists = this.assistanceApplication.getAssists();
        return ResponseEntity.ok(assists);
    }

}
