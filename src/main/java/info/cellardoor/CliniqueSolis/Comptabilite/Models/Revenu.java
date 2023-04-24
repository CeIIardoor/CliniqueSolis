package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "revenus")

public class Revenu {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer revenuId;
}
