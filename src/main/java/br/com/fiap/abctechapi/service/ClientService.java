package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Client;

public interface ClientService {
    Client createClient(Client client);

    Client getClientById(Long id);
}
