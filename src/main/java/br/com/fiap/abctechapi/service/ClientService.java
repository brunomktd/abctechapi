package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);

    Client getClientById(Long id);

    List<Client> getAllClients();
}
