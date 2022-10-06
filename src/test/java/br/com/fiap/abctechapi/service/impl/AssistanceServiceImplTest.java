package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AssistanceServiceImplTest {

    @InjectMocks
    private AssistanceServiceImpl assistanceService;

    @Mock
    private AssistanceRepository repository;

    @Test
    public void shouldCreateAssistanceWhenMethodCalled(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mockito.mock(Assistance.class));
        Assistance assistance = assistanceService.createAssistance(Assistance.builder().build());
        Assertions.assertNotNull(assistance);
    }

    @Test
    public void shouldReturnAlistOfAssistancesWhenMethodCalled(){
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(Mockito.mock(Assistance.class)));
        List<Assistance> assistanceList = assistanceService.getAssistanceList();
        Assertions.assertEquals(1, assistanceList.size());
    }
}