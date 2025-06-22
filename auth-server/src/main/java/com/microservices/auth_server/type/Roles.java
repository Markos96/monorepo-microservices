package com.microservices.auth_server.type;

public enum Roles {
    USER, ADMIN;


    public String getRole() {
        return name();
    }
}


