package info.cellardoor.CliniqueSolis.App.Http.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    private String cin;
    private String groupeSanguin;
    private String nom;
    private String prenom;
}