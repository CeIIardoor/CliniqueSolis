package info.cellardoor.CliniqueSolis.Patient.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import info.cellardoor.CliniqueSolis.Patient.Http.Response.PatientResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antecedents")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Antecedent {
    @Id
    @GeneratedValue
    private Integer antecedentId;
    private String groupeSanguin;
    private String allergies;
    private String maladiesChroniques;
    private String chirurgies;
    private String antecedentsFamiliaux;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ordonnance_id")
    private List<Ordonnance> ordonnances;
}
