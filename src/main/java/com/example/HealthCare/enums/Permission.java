package com.example.HealthCare.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),


    MEDECIN_READ_rendez_vous("medecin:rendez_vous:read"),
    MEDECIN_READ_dossiers_medicaux("medecin:dossiers_medicaux:read"),
    MEDECIN_CRETAE_diagnostic("medecin:diagnostic:create"),
    MEDECIN_UPDATE_observations("medecin:observations:update"),

    PATIENT_READ_profil("medecin:profil:read"),
    PATIENT_READ_rendez_vous("medecin:rendez_vous:read"),
    PATIENT_READ_dossiers_medicaux("medecin:dossiers_medicaux:read"),
    PATIENT_UPDATE_info_personnel("medecin:info_personnel:update"),

    ;

    @Getter
    private final String permission;
}
