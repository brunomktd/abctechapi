package br.com.fiap.abctechapi.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StatusOrderException extends ResponseStatusException {

    public StatusOrderException(HttpStatus status, String reason) {
        super(status, reason);
    }

}
