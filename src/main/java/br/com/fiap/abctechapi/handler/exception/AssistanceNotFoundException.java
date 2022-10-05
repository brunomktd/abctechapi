package br.com.fiap.abctechapi.handler.exception;

public class AssistanceNotFoundException extends RuntimeException{

    public AssistanceNotFoundException(Long assistRef) {
        super(String.format("A assistência '%s' não foi encontrada", assistRef));
    }
}
