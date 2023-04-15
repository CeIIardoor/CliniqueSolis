package info.cellardoor.CliniqueSolis.RendezVous.Http.Reponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;
import info.cellardoor.CliniqueSolis.Patient.Models.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RendezVousResponse {
    @JsonProperty("rendez_vous_id")
    private Integer rendezVousId;
    @JsonProperty("patient_id")
    private Patient patient;
    @JsonProperty("medecin_id")
    private Medecin medecin;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("heure")
    private Time heure;
    @JsonProperty("duree")
    private Integer duree;

}