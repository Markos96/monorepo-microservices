package com.microservices.auth_server.mapper;

import com.microservices.auth_server.data.domain.User;
import com.microservices.auth_server.data.dto.UserDTO;
import com.microservices.auth_server.type.Roles;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity (UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRol());

        return user;
    }
}
