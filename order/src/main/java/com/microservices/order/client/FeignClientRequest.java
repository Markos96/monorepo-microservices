package com.microservices.order.client;

import com.microservices.order.model.dto.ClientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "api-client-service")
public interface FeignClientRequest {

    @GetMapping("/client")
    List<ClientDTO> getClients();

    @GetMapping("/client/{id}")
    ClientDTO getClientById(@PathVariable("id") int id);
}
