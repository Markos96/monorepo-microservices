package com.microservices.auth_server.data.dto;

import com.microservices.auth_server.data.domain.Rol;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private List<Rol> rol;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rol> getRol() {
        return rol;
    }

    public void setRol(List<Rol> rol) {
        this.rol = rol;
    }
}
