package com.example.HealthCare.controller;

import com.example.HealthCare.dto.UserRequestDTO;
import com.example.HealthCare.dto.UserResponceDTO;
import com.example.HealthCare.model.User;
import com.example.HealthCare.service.UserDetailsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserDetailsServiceImpl userDetailsService;
    public UserController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService=userDetailsService;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponceDTO> register(@RequestBody UserRequestDTO user){
        return ResponseEntity.ok(userDetailsService.saveUser(user));
    }
}
