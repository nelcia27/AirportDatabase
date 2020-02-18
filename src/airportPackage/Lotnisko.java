package airportPackage;

public class Lotnisko {
    private String nazwa;
    private String miasto;
    private String kraj;

    Lotnisko(String nazwa, String miasto, String kraj){
        this.nazwa=nazwa;
        this.miasto=miasto;
        this.kraj=kraj;

    }

    public String getNazwa() {
        return nazwa;
    }

    public String getMiasto() {
        return miasto;
    }

    public String getKraj() {
        return kraj;
    }
}
