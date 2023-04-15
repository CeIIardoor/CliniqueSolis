package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Facture")

public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long facture_id;
    @JoinColumn(name="patientId")
    private Long patientId;
    private Long montant ;
    private String type_service;

}
