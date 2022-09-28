package br.com.fiap.abctechapi.handler.exception;

public class ClientNotFoundException extends RuntimeException {
    private final String description;

    public ClientNotFoundException(String message, String description) {
        super(message);
        this.description = description;
    }
}
