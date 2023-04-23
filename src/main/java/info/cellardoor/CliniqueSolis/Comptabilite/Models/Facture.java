package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Factures")
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer factureId;
    @JoinColumn(name="patientId")
    private Integer patientId;
    @OneToOne(cascade = CascadeType.ALL)
    private Patient patient;
    @OneToOne(cascade = CascadeType.ALL)
    private Medecin medecin;
    private Long montant ;
    private String type_service;

}
