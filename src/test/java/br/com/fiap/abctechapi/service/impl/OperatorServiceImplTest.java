package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.OperatorNotFoundException;
import br.com.fiap.abctechapi.model.Operator;
import br.com.fiap.abctechapi.repository.OperatorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OperatorServiceImplTest {

    @InjectMocks
    private OperatorServiceImpl service;

    @Mock
    private OperatorRepository repository;

    @Test
    public void shouldSaveOperatorWhenMethodCalled(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mockito.mock(Operator.class));
        Operator operator = service.saveOperator(Operator.builder().build());
        Assertions.assertNotNull(operator);
    }

    @Test
    public void shouldReturnAListOfOperatorWhenMethodCalled(){
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(Mockito.mock(Operator.class)));
        List<Operator> operatorList = service.getAllOperators();
        Assertions.assertEquals(1, operatorList.size());
    }

    @Test
    public void shouldGetOperatorById(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(Mockito.mock(Operator.class)));
        Operator operator = service.getOperatorById(1L);
        Assertions.assertNotNull(operator);
    }

    @Test
    public void shouldThrowClientNotFoundExceptionWhenClientDoesNotExists(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(OperatorNotFoundException.class, () -> service.getOperatorById(1L));
    }

}