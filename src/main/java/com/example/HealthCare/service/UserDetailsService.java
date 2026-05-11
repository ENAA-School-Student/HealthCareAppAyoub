package com.example.HealthCare.service;

import com.example.HealthCare.model.User;

public interface UserDetailsService {
   User loadUserByUsername(String username);
}
