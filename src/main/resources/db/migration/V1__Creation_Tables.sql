CREATE TABLE dossier_medical
(
    id            BIGINT  PRIMARY KEY AUTO_INCREMENT NOT NULL,
    diagnostic    VARCHAR(255)       ,
    observations  VARCHAR(255)       ,
    date_creation date               ,
    patient_id    INT
);

CREATE TABLE medecine
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom        VARCHAR(255)       ,
    telephone  VARCHAR(255)       ,
    email      VARCHAR(255)       ,
    specialite VARCHAR(255)
);

CREATE TABLE patient
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nom            VARCHAR(255)       ,
    prenom         VARCHAR(255)       ,
    email          VARCHAR(255)       ,
    telephone      VARCHAR(255)       ,
    date_naissance date
);

CREATE TABLE render_vous
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date_rendez_vous date               ,
    statut           VARCHAR(255)       ,
    medecine_id      INT                ,
    patient_id       INT
);