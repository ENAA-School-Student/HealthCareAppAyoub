package com.example.HealthCare.service;

import com.example.HealthCare.Exceptions.ResourceNotFoundException;
import com.example.HealthCare.dto.AuthenticationRequestDTO;
import com.example.HealthCare.dto.AuthenticationResponceDTO;
import com.example.HealthCare.dto.RegisterDTO;
import com.example.HealthCare.enums.Role;
import com.example.HealthCare.mapper.UserMapper;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.UserRepository;
import com.example.HealthCare.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.userMapper = userMapper;
        this.jwtService=jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User "+username+" not found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().getAuth())
                .build();
    }
    public AuthenticationResponceDTO getUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User "+username +" not found"));
        return userMapper.ToDTO(user);
    }

    public AuthenticationResponceDTO saveUser(RegisterDTO registerDTO){
        if (userRepository.findByUsername(registerDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        User user = userMapper.ToEntity(registerDTO);
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(Role.PATIENT);
        userRepository.save(user);

    var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponceDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponceDTO login( AuthenticationRequestDTO request){
        // the user is autheticated here:
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // if they are correct this happen ....
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new ResourceNotFoundException("User not found !!!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponceDTO.builder()
                .token(jwtToken)
                .build();
    }
}