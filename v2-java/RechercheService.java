import java.util.ArrayList;

public class RechercheService {
    private MedicamentRepository medicamentRepository;
    private PresentationRepository presentationRepository;

    public RechercheService(MedicamentRepository medicamentRepository, PresentationRepository presentationRepository) {
        this.medicamentRepository = medicamentRepository;
        this.presentationRepository = presentationRepository;
    }

    public void rechercherParNom(String recherche) {
        boolean trouve = false;
        ArrayList<Medicament> medicaments = medicamentRepository.getMedicaments();
        ArrayList<Presentation> presentations = presentationRepository.getPresentations();

        for (int i=0; i < medicaments.size() ; i++ ){
            Medicament med = medicaments.get(i);
            if (med.getName().contains(recherche)){
                trouve = true;
                for (int j = 0; j < presentations.size() ; j++ ){
                    Presentation pres = presentations.get(j);
                    if (med.getCodeCIS().equals(pres.getCodeCIS())){
                        if (pres.getTauxRemboursement().isEmpty()){
                        System.out.println( "Nom : " + med.getName() +" | Taux : Non communiqué ");
                        } else {
                        System.out.println( "Nom : " + med.getName() +" | Taux : "  + pres.getTauxRemboursement());

                        }
                        break;
                    }    
                }
            } 
        } 
        if (!trouve){ // Non trouvé 
            System.out.println("Médicament non trouvé.");
        }
    }
}