package br.com.fiap.abctechapi.handler;

import br.com.fiap.abctechapi.handler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

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

    @ExceptionHandler(StatusOrderException.class)
    public ResponseEntity<ErrorMessageResponse> badRequestToChangeStatusOrder(StatusOrderException exception){
        ErrorMessageResponse messageResponse = ErrorMessageResponse.builder()
                .message(exception.getMessage())
                .description(exception.getReason())
                .timestamp(new Date())
                .statusCode(exception.getStatus().value())
                .build();

        return new ResponseEntity<>(messageResponse, exception.getStatus());
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

}
