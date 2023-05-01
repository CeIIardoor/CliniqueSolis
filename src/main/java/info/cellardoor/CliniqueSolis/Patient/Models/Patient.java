package info.cellardoor.CliniqueSolis.Patient.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.cellardoor.CliniqueSolis.Auth.Models.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("patient_id") // maps patientId to patient_id in JSON
    private Integer patientId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore // ignores user field in JSON
    public User user;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "antecedent_id")
    @JsonProperty("antecedents") // maps antecedents to antecedents in JSON
    public Antecedent antecedents;

    @JsonProperty("cin") // maps cin to cin in JSON
    private String cin;

    @JsonProperty("telephone") // maps telephone to telephone in JSON
    private String telephone;

    @JsonProperty("sexe") // maps sexe to sexe in JSON
    private Sexe sexe;

    @JsonProperty("date_naissance") // maps dateNaissance to date_naissance in JSON
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateNaissance;

    @JsonProperty("age") // maps age to age in JSON
    public int getAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateNaissance, now);
        return period.getYears();
    }
}

