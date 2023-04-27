package info.cellardoor.CliniqueSolis.Consultation.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prescription {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prescriptionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consultation_id")
    private Consultation consultation;
    private String medicament;
    private Integer duree;

}
