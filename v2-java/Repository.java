import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Repository<T> {
    protected ArrayList<T> elements;

    public Repository() {
        this.elements = new ArrayList<>();
    }

    public void chargerDepuisFichier(String nomFichier) throws IOException {
        BufferedReader lecteur = new BufferedReader(new FileReader(nomFichier));
        String ligne;

        while ((ligne = lecteur.readLine()) != null) {
            String[] colonnes = ligne.split("\t", -1);
            T element = creerElement(colonnes);
            if (element != null) {
                elements.add(element);
            }
        }

        lecteur.close();
    }

    protected abstract T creerElement(String[] colonnes);

    public ArrayList<T> getElements() {
        return elements;
    }
}