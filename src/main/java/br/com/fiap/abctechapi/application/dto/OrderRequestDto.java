package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequestDto {
    private Long operatorId;
    private Long clientId;
    private List<Long> services;
    private OrderLocationDto start;
    private OrderLocationDto end;
}
