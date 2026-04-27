package com.example.HealthCare.repository;

import com.example.HealthCare.model.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

}
