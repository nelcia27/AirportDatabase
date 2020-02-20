package airportPackage;

public class Pasazer {
    private String pesel;
    private String imie;
    private String nazwisko;
    private String id_biletu;
    private int waga_bagazu;

    Pasazer(String pesel, String imie, String nazwisko, String id_biletu, int waga_bagazu){
        this.pesel=pesel;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.id_biletu=id_biletu;
        this.waga_bagazu=waga_bagazu;
    }

    public String getPesel() {
        return pesel;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getWaga_bagazu() {
        return waga_bagazu;
    }

    public String getId_biletu() {
        return id_biletu;
    }
}
