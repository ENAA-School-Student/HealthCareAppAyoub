ALTER TABLE dossier_medical
    ADD CONSTRAINT  uc_dossiermedical_patient UNIQUE (patient_id);
ALTER  TABLE dossier_medical
    ADD CONSTRAINT FK_dossiermedical_patient FOREIGN KEY (patient_id) REFERENCES patient (id);
ALTER TABLE render_vous
    ADD CONSTRAINT  FK_rendervous_medecine FOREIGN KEY (medecine_id)REFERENCES  medecine (id);
ALTER  TABLE  render_vous
    ADD CONSTRAINT FK_rendervous_patient FOREIGN KEY (patient_id) REFERENCES patient (id);