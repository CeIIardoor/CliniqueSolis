package info.cellardoor.CliniqueSolis.Consultation.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionResponse {
    @JsonProperty("prescription_id")
    private Integer prescriptionId;
    @JsonProperty("medicament")
    private String medicament;
    @JsonProperty("duree")
    private String duree;
}
