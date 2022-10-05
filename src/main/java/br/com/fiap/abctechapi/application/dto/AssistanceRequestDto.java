package br.com.fiap.abctechapi.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AssistanceRequestDto {
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name can't be empty")
    private String name;
    @NotNull(message = "description is mandatory")
    @NotEmpty(message = "description can't be empty")
    private String description;
}
