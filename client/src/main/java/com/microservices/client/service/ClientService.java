package com.microservices.client.service;

import com.microservices.client.mapper.ClientMapper;
import com.microservices.client.model.dto.ClientDTO;
import com.microservices.client.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    private ClientMapper clientMapper;

    public ClientService(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDTO> getClients() {
        return this.clientRepository.findAll()
                .stream()
                .map(client -> clientMapper.toDTO(client))
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(int id) {
        return this.clientRepository.findById(id)
                .map(client -> clientMapper.toDTO(client))
                .orElse(null);
    }
}
