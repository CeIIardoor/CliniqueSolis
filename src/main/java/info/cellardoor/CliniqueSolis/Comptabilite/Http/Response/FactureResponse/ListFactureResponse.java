package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ListFactureResponse {
    private List<FactureResponse> factures;
}
