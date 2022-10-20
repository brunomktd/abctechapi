package br.com.fiap.abctechapi.handler.exception;

public class ViaCepErrorException extends RuntimeException{
    public ViaCepErrorException(String message) {
        super(String.format("Error to search zipCode message: %s", message));
    }
}
