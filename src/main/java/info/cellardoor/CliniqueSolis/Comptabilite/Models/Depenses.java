package info.cellardoor.CliniqueSolis.Comptabilite.Models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@Entity
@Transactional
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "depenses")

public class Depenses {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer depensesId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "devis_id")
    private List<Devis> devisList ;




}
