package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    private Long operatorId;
    private List<AssistanceResponseDto> services;
    private OrderLocationDto start;
    private OrderLocationDto end;
}
