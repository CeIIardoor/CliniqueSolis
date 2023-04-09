package info.cellardoor.CliniqueSolis.Patient.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient{
    @Id @GeneratedValue
    private Integer patientId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
    private String groupeSanguin;
    private String allergies;
    private String maladiesChroniques;
    private String chirurgies;
    private String antecedentsFamiliaux;
    private String cin;
}
