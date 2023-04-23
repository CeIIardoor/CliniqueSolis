package info.cellardoor.CliniqueSolis.Comptabilite.Services;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FactureRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FactureResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.Facture;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FactureRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Builder
@Service
public class FactureService {
    public FactureRepository factureRepository;
    @Autowired
    public FactureService(FactureRepository factureRepository) {
        this.factureRepository = factureRepository;
    }

    public FactureResponse getFactureById(Integer id) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse createFacture(FactureRequest factureRequest ){

        var facture = Facture.builder()
                .montant(factureRequest.getMontant())
                .type_service(factureRequest.getType_service()).build();
        factureRepository.save(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse deleteFactureById(Integer id) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        factureRepository.delete(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
    public FactureResponse updateFactureById(Integer id, FactureRequest factureRequest) {
        var facture= factureRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non facture trouve"));
        facture.setMontant(factureRequest.getMontant());
        facture.setType_service(factureRequest.getType_service());
        factureRepository.save(facture);
        return FactureResponse.builder()
                .factureId(facture.getFactureId())
                .montant(facture.getMontant())
                .type_service(facture.getType_service())
                .build();

    }
}
