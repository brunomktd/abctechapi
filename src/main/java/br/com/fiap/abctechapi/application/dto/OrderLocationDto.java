package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class OrderLocationDto {
    @NotNull(message = "latitude is mandatory")
    private Double latitude;
    @NotNull(message = "longitude is mandatory")
    private Double longitude;
    @NotNull(message = "dateTime is mandatory")
    private Date dateTime;
}
