package com.example.HealthCare.service;

import com.example.HealthCare.Exceptions.ResourceNotFoundException;
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
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userMapper = userMapper;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User "+username+" not found"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
    public UserResponceDTO getUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("User "+username +" not found"));
        return userMapper.ToDTO(user);
    }

    public UserResponceDTO saveUser(UserRequestDTO userdto){
    User user = new User();
    user.setUsername(userdto.getUsername());
    user.setEmail(userdto.getEmail());
    user.setPassword(passwordEncoder.encode(userdto.getPassword()));
    return userMapper.ToDTO(userRepository.save(user));
    }
}