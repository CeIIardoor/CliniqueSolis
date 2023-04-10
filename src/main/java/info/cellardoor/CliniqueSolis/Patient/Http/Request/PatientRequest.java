package info.cellardoor.CliniqueSolis.Patient.Http.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private String email;
    private String mdp;
    private String cin;
    private String groupeSanguin;
    private String nom;
    private String prenom;
}