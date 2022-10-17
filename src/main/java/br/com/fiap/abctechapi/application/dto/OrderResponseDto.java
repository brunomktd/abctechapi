package br.com.fiap.abctechapi.application.dto;

import br.com.fiap.abctechapi.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Long operatorId;
    private ClientResponseDto client;
    private List<AssistanceResponseDto> services;
    private OrderLocationDto start;
    private OrderLocationDto end;
    private StatusEnum status;
    private LocalDateTime createdAt;
}
