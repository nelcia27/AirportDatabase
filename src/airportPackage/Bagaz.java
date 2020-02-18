package airportPackage;

public class Bagaz {
    private String id;
    private int waga;
    private String bilet_id;

    Bagaz(int waga,String bilet_id){
        //this.id=id;
        this.waga=waga;
        this.bilet_id=bilet_id;
    }

    public int getWaga() {
        return waga;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBilet_id() {
        return bilet_id;
    }
}
