import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        MedicamentRepository mdr = new MedicamentRepository();
        PresentationRepository pr = new PresentationRepository();
        RechercheService rs = new RechercheService(mdr, pr);

        try { 
            mdr.chargerDepuisFichier("CIS_bdpm_utf8.txt");
            pr.chargerDepuisFichier("CIS_CIP_bdpm.txt");
            System.out.println("Nombre de médicament chargés : " + mdr.getMedicaments().size());
            System.out.println("La Liste des 5 premiers médicaments");
            for (int i = 0; i < 5; i++){
                System.out.println("Le codeCIS du médicament : " + mdr.getMedicaments().get(i).getCodeCIS() + " et son nom : " + mdr.getMedicaments().get(i).getName());
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez le nom d'un médicament à rechercher : ");
            String recherche = scanner.nextLine();
            rs.rechercherParNom(recherche);

            scanner.close();
        } catch (IOException e){
            System.out.println("Erreur lors du chargement : " + e.getMessage());
        }
        
    }

    
}
