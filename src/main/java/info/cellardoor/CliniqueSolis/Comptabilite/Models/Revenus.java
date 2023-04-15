package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Revenus")

public class Revenus {


    @Id
    private Long id;


}
