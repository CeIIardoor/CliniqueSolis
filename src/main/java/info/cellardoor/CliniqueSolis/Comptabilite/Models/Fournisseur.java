package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Fournisseurs")

public class Fournisseur {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fournisseurId;
    private String nom_societe;
    private String email;


}
