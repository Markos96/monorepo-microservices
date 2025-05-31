package com.microservices.client.controller;

import com.microservices.client.model.dto.ClientDTO;
import com.microservices.client.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getClients() {
        return ResponseEntity.ok().body(this.clientService.getClients());
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(this.clientService.getClientById(id));
    }
}
