CREATE TABLE dossier_medical
(
    id            BIGINT  PRIMARY KEY AUTO_INCREMENT NOT NULL,
    diagnostic    VARCHAR(255)       ,
    observations  VARCHAR(255)       ,
    date_creation date               ,
    patient_id    BIGINT
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
    date_naissance BIGINT
);

CREATE TABLE render_vous
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    date_rendez_vous date               ,
    statut           VARCHAR(255)       ,
    medecine_id      BIGINT                ,
    patient_id       BIGINT
);
CREATE TABLE user
(
    id  BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);