package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssistanceResponseDto {
    private Long id;
    private String name;
    private String description;
}
