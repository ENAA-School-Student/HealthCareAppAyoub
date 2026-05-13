package com.example.HealthCare.service;

import com.example.HealthCare.dto.UserRequestDTO;
import com.example.HealthCare.dto.UserResponceDTO;
import com.example.HealthCare.mapper.UserMapper;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserResponceDTO findByuserName(String username) throws UsernameNotFoundException {
        User userExit = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User "+username+" not found"));
        return userMapper.ToDTO(userExit);
    }

    public UserResponceDTO saveUser(UserRequestDTO userdto){
    User user = new User();
    user.setUsername(userdto.getUsername());
    user.setEmail(userdto.getEmail());
    user.setPassword(passwordEncoder.encode(userdto.getPassword()));
    return userMapper.ToDTO(userRepository.save(user));
    }
}