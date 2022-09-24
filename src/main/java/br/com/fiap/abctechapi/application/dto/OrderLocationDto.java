package br.com.fiap.abctechapi.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class OrderLocationDto {
    private Double latitude;
    private Double longitude;
    private Date dateTime;
}
