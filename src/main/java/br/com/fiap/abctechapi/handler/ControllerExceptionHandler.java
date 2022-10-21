package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.application.dto.ErrorsDescriptionDto;
import br.com.fiap.abctechapi.handler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MinimumAssistsRequiredException.class)
    public ResponseEntity<ErrorMessageResponse> errorMinAssistRequired(MinimumAssistsRequiredException exception) {
        return this.getErrorMessage(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MaxAssistsException.class)
    public ResponseEntity<ErrorMessageResponse> errorMaxAssistante(MaxAssistsException exception) {
        return this.getErrorMessage(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AssistanceNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> assistanceNotFound(AssistanceNotFoundException exception) {
        return this.getErrorMessage("Assistance not found", exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperatorNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> assistanceNotFound(OperatorNotFoundException exception) {
        return this.getErrorMessage(exception.getMessage(), exception.getDescription(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> clientNotFound(ClientNotFoundException exception) {
        return this.getErrorMessage(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> orderIdNotFound(OrderNotFoundException exception) {
        return this.getErrorMessage(exception.getMessage(), exception.getDescription(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StatusOrderException.class)
    public ResponseEntity<ErrorMessageResponse> badRequestToChangeStatusOrder(StatusOrderException exception) {
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .message(exception.getMessage())
                .description(exception.getReason())
                .timestamp(new Date())
                .statusCode(exception.getStatus().value())
                .build();

        return new ResponseEntity<>(messageResponse, exception.getStatus());
    }

    @ExceptionHandler(ViaCepErrorException.class)
    public ResponseEntity<ErrorMessageResponse> viaCepError(ViaCepErrorException exception){
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .message(exception.getMessage())
                .description(exception.getMessage())
                .timestamp(new Date())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(messageResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorsDescriptionDto> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(ErrorsDescriptionDto.builder()
                    .field(fieldName)
                    .description(errorMessage)
                    .build());
        });

        return this.getErrorMessage("Invalid fields", HttpStatus.BAD_REQUEST, errors);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessage(String message, String description, HttpStatus statusHttp) {
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .message(message)
                .description(description)
                .timestamp(new Date())
                .statusCode(statusHttp.value())
                .build();

        return new ResponseEntity<>(messageResponse, statusHttp);
    }

    private ResponseEntity<ErrorMessageResponse> getErrorMessage(String message, HttpStatus statusHttp, List<ErrorsDescriptionDto> listErrors) {
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .message(message)
                .timestamp(new Date())
                .statusCode(statusHttp.value())
                .errors(listErrors)
                .build();

        return new ResponseEntity<>(messageResponse, statusHttp);
    }

}
