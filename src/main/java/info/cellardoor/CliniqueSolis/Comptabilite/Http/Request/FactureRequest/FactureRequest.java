package info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FactureRequest;

import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FactureRequest {
    private Integer nom;
    private Long montant ;
    private String type_service;
}
