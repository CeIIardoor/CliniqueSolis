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
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String mdp;
    private String role;
    private Integer antecedentId;
    private String telephone;
    private String sexe;
    private String dateNaissance;
}