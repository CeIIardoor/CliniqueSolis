package info.cellardoor.CliniqueSolis.Comptabilite.Http.Request;

import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FactureRequest {
    private Integer nom_patient;
    private Long montant ;
    private String type_service;
}
