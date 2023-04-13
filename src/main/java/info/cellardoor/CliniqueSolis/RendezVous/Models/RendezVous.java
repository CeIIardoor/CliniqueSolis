package info.cellardoor.CliniqueSolis.RendezVous.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "rendez_vous")

public class RendezVous {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id_patient;
    private  Long id_medecin;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
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
