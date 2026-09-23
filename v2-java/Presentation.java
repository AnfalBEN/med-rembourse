

public class Presentation {
    
    private String codeCIS;
    private String tauxRemboursement;

    public Presentation(String codeCIS, String tauxRemboursement){
        this.tauxRemboursement = tauxRemboursement;
        this.codeCIS = codeCIS;
    }

    public String getCodeCIS() {
        return codeCIS;
    }

    public String getTauxRemboursement() {
        return tauxRemboursement;
    }

}
