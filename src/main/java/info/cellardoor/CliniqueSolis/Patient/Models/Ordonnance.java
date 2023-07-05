package info.cellardoor.CliniqueSolis.Patient.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordonnance")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class Ordonnance {
    @Id
    @GeneratedValue
    private Integer ordonnance_id;
    private String medicaments;
    private String description;
    private String date;
}
