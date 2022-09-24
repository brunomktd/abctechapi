package br.com.fiap.abctechapi.handler.exception;

import lombok.Getter;

@Getter
public class OperatorNotFoundException extends RuntimeException{

    private final String description;
    public OperatorNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }
}
