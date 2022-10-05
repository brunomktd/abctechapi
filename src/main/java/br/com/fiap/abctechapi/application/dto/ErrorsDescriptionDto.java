package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorsDescriptionDto {
    private String field;
    private String description;
}
