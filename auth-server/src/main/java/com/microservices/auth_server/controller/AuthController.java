package com.microservices.auth_server.controller;

import com.microservices.auth_server.data.dto.AuthRequestDTO;
import com.microservices.auth_server.data.dto.LoginResponseDTO;
import com.microservices.auth_server.service.AuthService;
import com.microservices.auth_server.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String token = jwtUtils.generateToken(authentication.getName());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequestDTO authRequestDTO) {
        this.authService.registerUser(authRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
