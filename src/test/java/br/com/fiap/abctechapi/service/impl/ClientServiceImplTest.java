package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.ClientNotFoundException;
import br.com.fiap.abctechapi.model.Client;
import br.com.fiap.abctechapi.repository.ClientRepository;
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
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository repository;

    @Test
    public void shouldCreateClientWhenMethodCalled(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mockito.mock(Client.class));
        Client client = clientService.createClient(Client.builder().build());
        Assertions.assertNotNull(client);
    }

    @Test
    public void shouldGetClientById(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(Mockito.mock(Client.class)));
        Client client = clientService.getClientById(1L);
        Assertions.assertNotNull(client);
    }

    @Test
    public void shouldThrowClientNotFoundExceptionWhenClientDoesNotExists(){
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());
        Assertions.assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(1L));
    }

    @Test
    public void shouldGetAListOfClientsWhenMethodCalled(){
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(Mockito.mock(Client.class)));
        List<Client> allClients = clientService.getAllClients();
        Assertions.assertEquals(1, allClients.size());
    }

}