public class Medicament {

    private String codeCIS;
    private String name;

    public Medicament(String codeCIS, String name) {
        this.codeCIS = codeCIS;
        this.name = name;
    }

    public String getCodeCIS (){
        return codeCIS;
    }

    public String getName (){
        return name;
    }
}