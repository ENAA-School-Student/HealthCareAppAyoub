package com.example.HealthCare.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.HealthCare.enums.Permission.*;
@RequiredArgsConstructor
public enum Role {
    ADMIN (
            Set.of (
                    //ADMIN
                    ADMIN_READ_patient,
                    ADMIN_READ_medecin,
                    ADMIN_READ_rendez_vous,
                    ADMIN_READ_dossiers_medicaux,

                    ADMIN_CREATE_patient,
                    ADMIN_CREATE_medecin,
                    ADMIN_CREATE_dossiers_medicaux,
                    ADMIN_CREATE_diagnostic,
                    ADMIN_CREATE_rendez_vous,

                    ADMIN_UPDATE_patient,
                    ADMIN_UPDATE_medecin,
                    ADMIN_UPDATE_observations,
                    ADMIN_UPDATE_rendez_vous,

                    ADMIN_DELETE_patient,
                    ADMIN_DELETE_medecin,
                    ADMIN_DELETE_dossiers_medicaux,


                    //PATIENT

                    PATIENT_READ_profil,
                    PATIENT_READ_dossiers_medicaux,
                    PATIENT_READ_rendez_vous,
                    PATIENT_UPDATE_info_personnel,

                    //MEDECIN

                    MEDECIN_CRETAE_diagnostic,
                    MEDECIN_CREATE_dossiers_medicaux,
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
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authereties.add( new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authereties;
    }
}
