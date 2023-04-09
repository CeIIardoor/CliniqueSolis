package info.cellardoor.CliniqueSolis.App.Http.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponse {
    @JsonProperty("patient_id")
    private int patientId;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("groupe_sanguin")
    private String groupeSanguin;
}
