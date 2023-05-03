package info.cellardoor.CliniqueSolis.Comptabilite.Services;

import info.cellardoor.CliniqueSolis.Comptabilite.Http.Request.FournisseurRequest.FournisseurRequest;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.FournisseurResponse;
import info.cellardoor.CliniqueSolis.Comptabilite.Http.Response.FournisseurResponse.FournisseurResponse;

import info.cellardoor.CliniqueSolis.Comptabilite.Models.Fournisseur;
import info.cellardoor.CliniqueSolis.Comptabilite.Models.repositories.FournisseurRepository;
import info.cellardoor.CliniqueSolis.Patient.Models.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class FournisseurService {
    public final FournisseurRepository fournisseurRepository;
    public FournisseurResponse getFactureById(Integer id) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();
    }
    public FournisseurResponse createFacture(FournisseurRequest fournisseurRequest ){
        var fournisseur = Fournisseur.builder()
                        .nom(fournisseurRequest.getNom())
                                .prenom(fournisseurRequest.getPrenom())
                                        .email(fournisseurRequest.getEmail())
                                                .nom_societe(fournisseurRequest.getNom_societe()).build();
                
        fournisseurRepository.save(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }
    public FournisseurResponse deleteFactureById(Integer id) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        fournisseurRepository.delete(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }
    public FournisseurResponse updateFactureById(Integer id, FournisseurRepository fournisseurRepository) {
        var fournisseur= fournisseurRepository.findById(id).orElseThrow(() ->new NoSuchElementException("non fournisseur trouve"));
        fournisseurRepository.save(fournisseur);
        return FournisseurResponse.builder()
                .fournisseurId(fournisseur.getFournisseurId())
                .nom(fournisseur.getNom())
                .email(fournisseur.getEmail())
                .prenom(fournisseur.getPrenom())
                .nom_societe(fournisseur.getNom_societe())
                .build();

    }

//    public ListFactureResponse getAll() {
//        var factures = fournisseurRepository.findAll();
//        if (factures.size() == 0)
//            return null;
//        return ListFactureResponse.builder()
//                .factures(factures.stream().map(fournisseur -> FournisseurResponse.builder()
//                                .factureId(fournisseur.getFactureId())
//                                .montant(fournisseur.getMontant())
//                                .cin(fournisseur.getPatient().getCin())
//                                .type_service(fournisseur.getType_service())
//                                .build())
//                        .toList())
//                .build();
//    }
}
