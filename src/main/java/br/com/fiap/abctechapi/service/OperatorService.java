package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.handler.exception.OperatorNotFoundException;
import br.com.fiap.abctechapi.model.Operator;

import java.util.List;

public interface OperatorService {
    Operator saveOperator(Operator operator);

    List<Operator> getAllOperators();

    Operator getOperatorById(Long operationId) throws OperatorNotFoundException;
}
