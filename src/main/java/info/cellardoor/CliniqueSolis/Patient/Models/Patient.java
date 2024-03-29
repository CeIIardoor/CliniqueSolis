package info.cellardoor.CliniqueSolis.Patient.Models;

import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient{
    @Id @GeneratedValue
    private Integer patientId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    public User user;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedent_id")
    public Antecedent antecedents;
    private String cin;
    private String telephone;
    private Sexe sexe;
    private String dateNaissance;

    public Integer getAge() {
        Date date = new Date();
        return 2023 - Integer.parseInt(dateNaissance.split("-")[0]);
    }


}