package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tresorerie")

public class Tresorerie {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tresorerieId;
    public static long capital ;


}

