package com.example.HealthCare.controller;

import com.example.HealthCare.model.User;
import com.example.HealthCare.service.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private UserDetailsServiceImpl userDetailsService;
    public UserController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService=userDetailsService;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userDetailsService.saveUser(user);
    }
}
