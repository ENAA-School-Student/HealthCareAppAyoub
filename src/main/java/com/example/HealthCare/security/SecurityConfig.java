package com.example.HealthCare.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.HealthCare.enums.Permission.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     return   http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(  "/api/auth/**",
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html").permitAll()

                                .requestMatchers(HttpMethod.GET,"/api/patients/**").hasAnyAuthority(ADMIN_READ_profil.getPermission(),PATIENT_READ_profil.getPermission())
                                .requestMatchers(HttpMethod.POST,"/api/patients/**").hasAuthority(ADMIN_CREATE_patient.getPermission())
                                .requestMatchers(HttpMethod.PUT,"/api/patients/**").hasAnyAuthority(ADMIN_UPDATE_info_personnel.getPermission(),PATIENT_UPDATE_info_personnel.getPermission())
                                .requestMatchers(HttpMethod.DELETE,"/api/patients/**").hasAuthority(ADMIN_DELETE_patient.getPermission())

                                .requestMatchers(HttpMethod.GET,"/api/medecine/**").hasAuthority(ADMIN_READ_medecin.getPermission())
                                .requestMatchers(HttpMethod.POST,"/api/medecine/**").hasAuthority(ADMIN_CREATE_medecin.getPermission())
                                .requestMatchers(HttpMethod.PUT,"/api/medecine/**").hasAuthority(ADMIN_UPDATE_medecin.getPermission())
                                .requestMatchers(HttpMethod.DELETE,"/api/medecine/**").hasAuthority(ADMIN_DELETE_medecin.getPermission())


                                .requestMatchers(HttpMethod.GET,"/api/RendezVous/**").hasAnyAuthority(
                                        ADMIN_READ_rendez_vous.getPermission(),
                                        MEDECIN_READ_rendez_vous.getPermission(),
                                        PATIENT_READ_rendez_vous.getPermission())

                                .requestMatchers(HttpMethod.POST,"/api/RendezVous/**").hasAuthority(ADMIN_CREATE_rendez_vous.getPermission())
                                .requestMatchers(HttpMethod.PUT,"/api/RendezVous/**").hasAuthority(ADMIN_UPDATE_rendez_vous.getPermission())

                                .requestMatchers(HttpMethod.GET,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_READ_dossiers_medicaux.getPermission(),
                                        PATIENT_READ_dossiers_medicaux.getPermission(),
                                        MEDECIN_READ_dossiers_medicaux.getPermission())

                                .requestMatchers(HttpMethod.POST,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_CREATE_dossiers_medicaux.getPermission(),
                                        ADMIN_CREATE_diagnostic.getPermission(),
                                        MEDECIN_CREATE_dossiers_medicaux.getPermission(),
                                        MEDECIN_CREATAE_diagnostic.getPermission())

                                .requestMatchers(HttpMethod.PUT,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_UPDATE_observations.getPermission(),
                                        MEDECIN_UPDATE_observations.getPermission())

                                .requestMatchers(HttpMethod.GET, "/api/download/**").hasAuthority(ADMIN_READ_dossiers_medicaux.getPermission())

                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

             .build();
    }

}
