package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class OrderRequestDto {
    @NotNull(message = "operatorId is mandatory")
    private Long operatorId;
    @NotNull(message = "clientId is mandatory")
    private Long clientId;
    @NotNull(message = "services is mandatory")
    private List<Long> services;
}
