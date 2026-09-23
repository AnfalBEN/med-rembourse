
import java.util.ArrayList;

public class MedicamentRepository extends Repository<Medicament> {
    @Override
    protected Medicament creerElement(String[] colonnes) {
        if (colonnes.length > 2) {
                Medicament nm = new Medicament(colonnes[0], colonnes[1]); 
                return nm; 
        } else {
            return null;
        }        
        
    }

    public ArrayList<Medicament> getMedicaments() {
        return getElements();
    }
}
