package com.microservices.auth_server.service;

import com.microservices.auth_server.data.domain.Rol;
import com.microservices.auth_server.data.dto.AuthRequestDTO;
import com.microservices.auth_server.data.dto.UserDTO;
import com.microservices.auth_server.mapper.UserMapper;
import com.microservices.auth_server.repository.UserRepository;
import com.microservices.auth_server.type.Roles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;

    public AuthService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, RolService rolService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void registerUser(AuthRequestDTO authRequestDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(authRequestDTO.getUsername());
        userDTO.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        userDTO.setRol(List.of(rolService.getRolByName("ROLE_USER")));

        this.userRepository.save(this.userMapper.toEntity(userDTO));
    }
}
