package com.example.HealthCare.service;

import com.example.HealthCare.Exceptions.ResourceNotFoundException;
import com.example.HealthCare.dto.*;
import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    RendezVousMapper rendezVousMapper;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PatientRepository patientRepository;
//    @Autowired
//    private MedecinMapper medecinMapper;
//    @Autowired
//    private PatientMapper patientMapper;
    @Autowired
    private DossierMedicalMapper dossierMedicalMapper;


    public RendezVousResponseDTO ajouterRendezVous(RendezVousRequestDTO rendezVousRequestDTO)
    {
        Medecine medecin = medecinRepository.findById(rendezVousRequestDTO.getMedecinId()).orElseThrow(() -> new ResourceNotFoundException("Medecin Not found "));
        Patient patient = patientRepository.findById(rendezVousRequestDTO.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient Not found "));

        RendezVous rendezVous = rendezVousMapper.ToEntity(rendezVousRequestDTO);
        rendezVous.setMedecine(medecin);
        rendezVous.setPatient(patient);

        RendezVous save = rendezVousRepository.save(rendezVous);

        return rendezVousMapper.ToDTO(save);
    }

    public RendezVousResponseDTO modifieRendezVous(Long id, RendezVousRequestDTO rendezVousRequestDTO){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("RendzeVous Not found with id  :"+id));
        Medecine medecine = medecinRepository.findById(rendezVousRequestDTO.getMedecinId()).orElseThrow(()->new ResourceNotFoundException("medecine Not found with id  :"+id));
        Patient patient = patientRepository.findById(rendezVousRequestDTO.getPatientId()).orElseThrow(()-> new ResourceNotFoundException("Patient Not found with id  :"+id));
        rendezVous.setMedecine(medecine);
        rendezVous.setPatient(patient);
        rendezVous.setStatut(rendezVousRequestDTO.getStatut());
        rendezVous.setDateRendezVous(rendezVousRequestDTO.getDateRendezVous());

        RendezVous saveUpdatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveUpdatedRendezVous);
    }

    public RendezVousResponseDTO modifieRendezVousStatut(Long id , Statut statut){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RendzeVous Not found with id  :"+id));
        rendezVous.setStatut(statut);

        RendezVous save = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(save);
    }


    public RendezVousResponseDTO AnnulerRendezVous(long id){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("RendzeVous Not found with id  :"+id));

        rendezVous.setStatut(Statut.ANNULE);

        RendezVous saveAnuller = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveAnuller);
    }

    public List<RendezVousResponseDTO> obtenirTousLesRendezVous(){
        List<RendezVous> rendezVous = rendezVousRepository.findAll();
        List<RendezVousResponseDTO> rendezVousResponseDTOS = new ArrayList<>();
        for(RendezVous RV: rendezVous){
            rendezVousResponseDTOS.add(rendezVousMapper.ToDTO(RV));
        }
        return rendezVousResponseDTOS;

    }
public  List<RendezVousResponseDTO> rechercherParPatient(long id){
        List<RendezVousResponseDTO> GetResault = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousRepository.findByPatient_Id(id) ){
            GetResault.add(rendezVousMapper.ToDTO(rendezVous));
        }
        return GetResault;
}

public List<RendezVousResponseDTO> rechercherParMedecine(long id){
        List<RendezVousResponseDTO> GetResaul = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousRepository.findByMedecine_Id(id)){
            GetResaul.add(rendezVousMapper.ToDTO(rendezVous));
        }
        return GetResaul;
}


public List<RendezVousResponseDTO> rendezVousParStatut(Statut statut){
        return rendezVousRepository.findAll()
                .stream()
                .filter(rendezvous -> rendezvous.getStatut().equals(statut))
                .map(rendezVous -> rendezVousMapper.ToDTO(rendezVous)).toList();
}

public List<RendezVousResponseDTO> findRendzeVousByMedecinID(long id){
        return rendezVousRepository.findRendezVousDeUnMedecine(id)
                .stream()
                .map(rendezVous -> rendezVousMapper.ToDTO(rendezVous)).toList();

}











//public List<RendezVousResponseDTO> getAllrendezVousParUnDate(LocalDate date){
//       return rendezVousRepository.rendezVousPourUnmedecinParUnDate(date)
//        .stream()
//               .map(renderVous -> rendezVousMapper.ToDTO(renderVous)
//               )
//               .toList();
//}
//
//public List<RendezVousResponseDTO> getallPatientRendzeVous(){
//        return rendezVousRepository.patietnRendezVous()
//                .stream()
//                .map(rendzevous -> rendezVousMapper.ToDTO(rendzevous)).toList();
//}
//
//public List<MedecinResponseDTO> getALLMedecineRendezVouus(){
//        return rendezVousRepository.allRendezvVousDeUnmedecein()
//                .stream()
//                .map(medcienrendezVous -> medecinMapper.ToDTO(medcienrendezVous)).toList();
//}
//
//public List<PatientResponseDTO> getALlRendezVousGretaerTHAN(int number){
//        return rendezVousRepository.getAllPatietRendezVousGreaterThan(number)
//                .stream()
//                .map(patient -> patientMapper.toDTO(patient)).toList();
//}
//
//public List<RendezVousResponseDTO> getAllRendezVousAfterToday(){
//        return rendezVousRepository.rendez_vousapreraujourdhui()
//                .stream()
//                .map(rendezvou -> rendezVousMapper.ToDTO(rendezvou)).toList();
//}



}
