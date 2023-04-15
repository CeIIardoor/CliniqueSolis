package info.cellardoor.CliniqueSolis.RendezVous.Models;

import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RendezVous {
    @Id
    @GeneratedValue
    private Integer rendezVousId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public Patient patient;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public Medecin medecin;
    private String date;
    private String heure;
    @Column
    private Integer duree;
}