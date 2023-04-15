package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Tresorerie")

public class Tresorerie {

    @Id
    private Integer id;
    public static long capital ;


}

