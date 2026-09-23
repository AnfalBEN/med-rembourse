import java.util.ArrayList;

public class PresentationRepository extends Repository<Presentation> {

    @Override 
    protected Presentation creerElement (String[] colonnes){
        if (colonnes.length > 8){
            Presentation pr = new Presentation(colonnes[0], colonnes[8]); 
            return pr;
        } else {
            return null; 
        }
    }

    public ArrayList<Presentation> getPresentations() {
        return getElements();
    }
}