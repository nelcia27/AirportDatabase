package airportPackage;

import java.util.List;

public class Zaloga {
    private List<Osoba> czlonkowie;

    Zaloga(List<Osoba> czlonkowie)
    {
        this.czlonkowie=czlonkowie;
    }

    public List<Osoba> getCzlonkowie() {
        return czlonkowie;
    }
}
