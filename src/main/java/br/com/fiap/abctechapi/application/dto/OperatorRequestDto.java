package br.com.fiap.abctechapi.application.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OperatorRequestDto {
    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name can't be empty")
    private String name;
}
