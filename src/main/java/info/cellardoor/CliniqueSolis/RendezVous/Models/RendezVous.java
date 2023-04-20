package info.cellardoor.CliniqueSolis.RendezVous.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RendezVous {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nom;
    @Column(length = 50)
    private String prenom;
    @Column(length = 50)
    private String cin;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private LocalDate heure;
}
