package airportPackage;

public class Osoba {
    private String pesel;
    private String imie;
    private String nazwisko;
    private String rola;
    private String id_lotu;

    Osoba(String pesel, String imie, String nazwisko, String rola){
        this.pesel=pesel;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.rola=rola;
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

    public String getRola() {
        return rola;
    }

    public String getId_lotu() {
        return id_lotu;
    }

    public void setId_lotu(String id_lotu) {
        this.id_lotu = id_lotu;
    }
}
