package com.example.HealthCare.security;

import com.example.HealthCare.enums.Role;
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

                                .requestMatchers(HttpMethod.GET,"/api/patients/**").hasAnyAuthority(ADMIN_READ_profil.name(),PATIENT_READ_profil.name())
                                .requestMatchers(HttpMethod.POST,"/api/patients/**").hasAuthority(ADMIN_CREATE_patient.name())
                                .requestMatchers(HttpMethod.PUT,"/api/patients/**").hasAnyAuthority(ADMIN_UPDATE_info_personnel.name(),PATIENT_UPDATE_info_personnel.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/patients/**").hasAuthority(ADMIN_DELETE_patient.name())

                                .requestMatchers(HttpMethod.GET,"/api/medecine/**").hasAuthority(ADMIN_READ_medecin.name())
                                .requestMatchers(HttpMethod.POST,"/api/medecine/**").hasAuthority(ADMIN_CREATE_medecin.name())
                                .requestMatchers(HttpMethod.PUT,"/api/medecine/**").hasAuthority(ADMIN_UPDATE_medecin.name())
                                .requestMatchers(HttpMethod.DELETE,"/api/medecine/**").hasAuthority(ADMIN_DELETE_medecin.name())


                                .requestMatchers(HttpMethod.GET,"/api/RendezVous/**").hasAnyAuthority(
                                        ADMIN_READ_rendez_vous.name(),
                                        MEDECIN_READ_rendez_vous.name(),
                                        PATIENT_READ_rendez_vous.name())

                                .requestMatchers(HttpMethod.POST,"/api/RendezVous/**").hasAuthority(ADMIN_CREATE_rendez_vous.name())
                                .requestMatchers(HttpMethod.PUT,"/api/RendezVous/**").hasAuthority(ADMIN_UPDATE_rendez_vous.name())

                                .requestMatchers(HttpMethod.GET,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_READ_dossiers_medicaux.name(),
                                        PATIENT_READ_dossiers_medicaux.name(),
                                        MEDECIN_READ_dossiers_medicaux.name())

                                .requestMatchers(HttpMethod.POST,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_CREATE_dossiers_medicaux.name(),
                                        ADMIN_CREATE_diagnostic.name(),
                                        MEDECIN_CREATE_dossiers_medicaux.name(),
                                        MEDECIN_CRETAE_diagnostic.name())

                                .requestMatchers(HttpMethod.PUT,"/api/DossierMedical/**").hasAnyAuthority(
                                        ADMIN_UPDATE_observations.name(),
                                        MEDECIN_UPDATE_observations.name())



                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

             .build();
    }

}
