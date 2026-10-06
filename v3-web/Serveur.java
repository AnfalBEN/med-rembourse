import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Serveur {
    public static void main(String[] args) throws IOException {
        MedicamentRepository mdr = new MedicamentRepository();
        PresentationRepository pr = new PresentationRepository();
        mdr.chargerDepuisFichier("CIS_bdpm_utf8.txt");
        pr.chargerDepuisFichier("CIS_CIP_bdpm.txt");

        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer serveur = HttpServer.create(new InetSocketAddress(port), 0);

        serveur.createContext("/recherche", (exchange) -> {
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            String query = exchange.getRequestURI().getQuery();  // ex: "nom=doliprane"
            String nom = query.split("=")[1];                     // récupère juste "doliprane"

            ArrayList<Medicament> medicaments = mdr.getMedicaments();
            ArrayList<Presentation> presentations = pr.getPresentations();

            StringBuilder json = new StringBuilder();
            json.append("[");
            boolean premier = true;

            for (int i = 0; i < medicaments.size(); i++) {
                Medicament med = medicaments.get(i);
                if (med.getName().toLowerCase().contains(nom.toLowerCase())) {
                    for (int j = 0; j < presentations.size(); j++) {
                        Presentation pres = presentations.get(j);
                        if (med.getCodeCIS().equals(pres.getCodeCIS())) {
                            if (!premier) {
                                json.append(",");
                            }
                            String taux = pres.getTauxRemboursement();
                            boolean rembourse = !taux.isEmpty();
                            String tauxAffiche = rembourse ? taux.replace("%", "") : "0";

                            json.append("{\"nom\":\"").append(med.getName().replace("\"", "'")).append("\",");
                            json.append("\"forme\":\"comprimé\",");
                            json.append("\"rembourse\":").append(rembourse).append(",");
                            json.append("\"taux\":").append(tauxAffiche).append("}");

                            premier = false;
                            break;
                        }
                    }
                }
            }
            json.append("]");

            String reponse = json.toString();
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");

            byte[] bytes = reponse.getBytes("UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(bytes);
            os.close();     
        });

        serveur.start();
        System.out.println("Serveur démarré sur http://localhost:8080");
    }
}