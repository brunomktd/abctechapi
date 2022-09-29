package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.application.dto.ErrorsDescriptionDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.NonNullFields;

import java.util.Date;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageResponse {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
    private List<ErrorsDescriptionDto> errors;

}
