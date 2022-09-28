package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.ClientNotFoundException;
import br.com.fiap.abctechapi.model.Client;
import br.com.fiap.abctechapi.repository.ClientRepository;
import br.com.fiap.abctechapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()){
            throw new ClientNotFoundException("Client is invalid", "O cliente informado não existe");
        }
        return clientOptional.get();
    }
}
