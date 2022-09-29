package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OperatorApplication;
import br.com.fiap.abctechapi.application.dto.OperatorRequestDto;
import br.com.fiap.abctechapi.application.dto.OperatorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("operators")
public class OperatorController {

    @Autowired
    private OperatorApplication operatorApplication;

    @PostMapping
    public ResponseEntity<OperatorResponseDto> createOperator(@Valid @RequestBody OperatorRequestDto operatorRequestDto) {
        OperatorResponseDto operator = operatorApplication.createOperator(operatorRequestDto);
        URI uri = UriComponentsBuilder.fromPath("/operators/{id}").buildAndExpand(operator.getOperatorId()).toUri();
        return ResponseEntity.created(uri).body(operator);
    }

    @GetMapping
    public ResponseEntity<List<OperatorResponseDto>> getOperators () {
        List<OperatorResponseDto> allOperators = operatorApplication.getAllOperators();
        return ResponseEntity.ok(allOperators);
    }

}
