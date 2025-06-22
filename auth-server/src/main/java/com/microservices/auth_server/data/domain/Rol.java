package com.microservices.auth_server.data.domain;

import com.microservices.auth_server.type.Roles;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;


    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
