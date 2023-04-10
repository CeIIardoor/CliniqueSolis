package info.cellardoor.CliniqueSolis.Patient.Models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByPatientId(Integer patientId);

}