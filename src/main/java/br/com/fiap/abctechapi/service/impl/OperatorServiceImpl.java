package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.OperatorNotFoundException;
import br.com.fiap.abctechapi.model.Operator;
import br.com.fiap.abctechapi.repository.OperatorRepository;
import br.com.fiap.abctechapi.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {

    @Autowired
    private OperatorRepository repository;

    @Override
    public Operator saveOperator(Operator operator) {
        return repository.save(operator);
    }

    @Override
    public List<Operator> getAllOperators() {
        return repository.findAll();
    }

    @Override
    public Operator getOperatorById(Long operatorId) throws OperatorNotFoundException {
        Optional<Operator> operatorById = repository.findById(operatorId);
        if (operatorById.isEmpty()){
            throw new OperatorNotFoundException("Operator is invalid", "O operadorId informado não existe");
        }
        return operatorById.get();
    }
}
