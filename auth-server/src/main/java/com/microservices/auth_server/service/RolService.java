package com.microservices.auth_server.service;

import com.microservices.auth_server.data.domain.Rol;
import com.microservices.auth_server.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private RolRepository rolRepository;

    public Rol getRolByName(String rolName) {
        return this.rolRepository.findByRole(rolName).orElse(null);
    }

    @Autowired
    public void setRolRepository(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
}
