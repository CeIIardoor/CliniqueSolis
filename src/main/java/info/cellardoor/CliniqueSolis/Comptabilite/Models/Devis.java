package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Devis")

public class Devis {

    @Id @GeneratedValue
    private Integer devis_id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fournisseur_id")
    private  Fournisseur fournisseur;
    private String montant;
    private String type_achat;
    private Status status ;

}
