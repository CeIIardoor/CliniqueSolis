package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor


public class FactureResponse {

    @JsonProperty("factureId")
    private Integer factureId;

    @JsonProperty("nom_patient")
    private String nom;

    @JsonProperty("montant")
    private Long montant ;

    @JsonProperty("type_service")
    private String type_service;


}
