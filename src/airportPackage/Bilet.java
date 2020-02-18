package airportPackage;

public class Bilet {
    public String getId() {
        return id;
    }

    private String id;
    public Lot lot;

    Bilet(String id){
        this.id=id;
    }
}
