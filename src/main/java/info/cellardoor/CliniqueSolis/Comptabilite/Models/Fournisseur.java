package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fournisseur")

public class Fournisseur {

    @Id @GeneratedValue
    private Integer fournisseur_id;
    private String nom_societe;
    private String email;


}
