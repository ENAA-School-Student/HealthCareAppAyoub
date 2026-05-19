package com.example.HealthCare.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.example.HealthCare.enums.Permission.*;
@RequiredArgsConstructor
public enum Role {
    ADMIN (
            Set.of (
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,

                    PATIENT_READ_profil,
                    PATIENT_READ_dossiers_medicaux,
                    PATIENT_READ_rendez_vous,
                    PATIENT_UPDATE_info_personnel,

                    MEDECIN_CRETAE_diagnostic,
                    MEDECIN_READ_rendez_vous,
                    MEDECIN_READ_dossiers_medicaux,
                    MEDECIN_UPDATE_observations
    )),
    PATIENT(
            Set.of (
                    PATIENT_READ_profil,
                    PATIENT_READ_dossiers_medicaux,
                    PATIENT_READ_rendez_vous,
                    PATIENT_UPDATE_info_personnel
            )
    ),
    MEDECIN(
            Set.of(
                    MEDECIN_CRETAE_diagnostic,
                    MEDECIN_READ_rendez_vous,
                    MEDECIN_READ_dossiers_medicaux,
                    MEDECIN_UPDATE_observations
            )
    );
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuth(){
        var authereties = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authereties.add( new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authereties;
    }
}
