package info.cellardoor.CliniqueSolis.Medecin.Service;

import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Http.Response.MedecinResponse;
import info.cellardoor.CliniqueSolis.Medecin.Models.Medecin;

public class MedecinDTO {
    public static MedecinResponse build (Medecin medecin){
        return MedecinResponse.builder()
                .medecinId(medecin.getMedecinId())
                .nom(medecin.getUser().getNom())
                .prenom(medecin.getUser().getPrenom())
                .cin(medecin.getCin())
                .telephone(medecin.getTelephone())
                .email(medecin.getUser().getEmail())
                .specialite(medecin.getSpecialite())
                .diplome(medecin.getDiplome())
                .build();
    }
}