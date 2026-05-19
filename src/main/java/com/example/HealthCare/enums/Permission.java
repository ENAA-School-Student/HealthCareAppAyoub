package com.example.HealthCare.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    //ADMIN

    ADMIN_READ_rendez_vous("admin:rendez_vous:read"),
    ADMIN_READ_dossiers_medicaux("admin:dossiers_medicaux:read"),
    ADMIN_READ_patient("admin:patient:read"),

    ADMIN_UPDATE_rendez_vous("admin:rendez_vous:update"),
    ADMIN_UPDATE_dossiers_medicaux("admin:dossiers_medicaux:update"),
    ADMIN_UPDATE_patient("admin:patient:update"),

    ADMIN_CREATE_rendez_vous("admin:rendez_vous:create"),
    ADMIN_CREATE_dossiers_medicaux("admin:dossiers_medicaux:create"),
    ADMIN_CREATE_patient("admin:patient:create"),

    ADMIN_DELETE("admin:delete"),
    ADMIN_DELETE_rendez_vous("admin:rendez_vous:delete"),
    ADMIN_DELETE_dossiers_medicaux("admin:dossiers_medicaux:delete"),
    ADMIN_DELETE_patient("admin:patient:delete"),

    //MEDECIN

    MEDECIN_READ_rendez_vous("medecin:rendez_vous:read"),
    MEDECIN_READ_dossiers_medicaux("medecin:dossiers_medicaux:read"),
    MEDECIN_CRETAE_diagnostic("medecin:diagnostic:create"),
    MEDECIN_UPDATE_observations("medecin:observations:update"),

    //PATIENT

    PATIENT_READ_profil("medecin:profil:read"),
    PATIENT_READ_rendez_vous("medecin:rendez_vous:read"),
    PATIENT_READ_dossiers_medicaux("medecin:dossiers_medicaux:read"),
    PATIENT_UPDATE_info_personnel("medecin:info_personnel:update"),

    ;

    @Getter
    private final String permission;
}
