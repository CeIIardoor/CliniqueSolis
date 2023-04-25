package info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListFactureResponse {
    private List<FactureResponse> factures;
}
