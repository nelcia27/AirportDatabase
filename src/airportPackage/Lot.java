package airportPackage;

//import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;

import java.sql.Date;

public class Lot {
    private String id_lotu;
    private Date data_wylotu;
    private Date data_przylotu;
    private String z_do;
    private int brama;
    private String liniaLotnicza;
    private String skomunikowaneLotnisko;
    private String samolot;


    Lot(/*String id_lotu, */Date data_wylotu, Date data_przylotu, String z_do, int brama, String liniaLotnicza, String skomunikowaneLotnisko, String samolot){
        //.id_lotu=id_lotu;
        this.data_wylotu=data_wylotu;
        this.data_przylotu=data_przylotu;
        this.z_do=z_do;
        this.brama=brama;
        this.liniaLotnicza=liniaLotnicza;
        this.skomunikowaneLotnisko=skomunikowaneLotnisko;
        this.samolot=samolot;
    }

    public Date getData_wylotu() {
        return data_wylotu;
    }

    public Date getData_przylotu() {
        return data_przylotu;
    }

    public String getZ_do() {
        return z_do;
    }

    public int getBrama() {
        return brama;
    }

    public String getLiniaLotnicza() {
        return liniaLotnicza;
    }

    public String getSkomunikowaneLotnisko() {
        return skomunikowaneLotnisko;
    }

    public String getSamolot() {
        return samolot;
    }

    public String getId_lotu() {
        return id_lotu;
    }

    public void setId_lotu(String id_lotu) {
        this.id_lotu = id_lotu;
    }
}

