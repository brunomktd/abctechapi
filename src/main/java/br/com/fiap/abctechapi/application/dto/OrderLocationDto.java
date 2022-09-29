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
    @NotEmpty(message = "latitude can't be empty")
    private Double latitude;
    @NotNull(message = "longitude is mandatory")
    @NotEmpty(message = "longitude can't be empty")
    private Double longitude;
    @NotNull(message = "dateTime is mandatory")
    @NotEmpty(message = "dateTime can't be empty")
    private Date dateTime;
}
