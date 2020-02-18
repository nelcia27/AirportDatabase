package airportPackage;

public class Samolot {
    private int ladownosc;
    private int liczbaMiejsc;

    Samolot(int ladownosc,int liczbaMiejsc){
        this.ladownosc=ladownosc;
        this.liczbaMiejsc=liczbaMiejsc;
    }

    public int getLadownosc() {
        return ladownosc;
    }

    public int getLiczbaMiejsc() {
        return liczbaMiejsc;
    }
}
