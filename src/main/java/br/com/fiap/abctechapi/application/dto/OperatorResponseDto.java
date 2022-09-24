package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OperatorResponseDto {
    private Long operatorId;
    private String name;
}
