package info.cellardoor.CliniqueSolis.RendezVous.Http.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RendezVousRequest {
    @JsonProperty("rendezVous_id")
    private Integer rendezVousId;
    private String date;
    private String heure;
    private Integer duree;
    @JsonProperty("medecin_id")
    private Integer medecinId;
    @JsonProperty("patient_id")
    private Integer patientId;
}