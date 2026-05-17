package com.example.HealthCare.controller;

import com.example.HealthCare.dto.AuthenticationRequestDTO;
import com.example.HealthCare.dto.AuthenticationResponceDTO;
import com.example.HealthCare.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService userDetailsService){
        this.authenticationService=userDetailsService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponceDTO> register(@Valid @RequestBody AuthenticationRequestDTO user){
        return ResponseEntity.ok(authenticationService.saveUser(user));
    }
    @GetMapping("/userByName")
    public ResponseEntity<AuthenticationResponceDTO> findByUserName(@Valid @RequestParam String username){
        return ResponseEntity.ok(authenticationService.getUser(username));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponceDTO> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO){
        return ResponseEntity.ok(authenticationService.login(authenticationRequestDTO));
    }
}