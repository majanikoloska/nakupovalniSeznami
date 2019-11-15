package DTO;

import Entities.ArtikelEntity;

import java.util.List;

public class NakupovalniSeznamDto {

    public int id;
    public String status;
    public String opomba;
    public String naziv;
    public List<ArtikelEntity> artikli;
    public Integer uporabnikId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpomba() {
        return opomba;
    }

    public void setOpomba(String opomba) {
        this.opomba = opomba;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<ArtikelEntity> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<ArtikelEntity> artikli) {
        this.artikli = artikli;
    }

    public Integer getUporabnikId() {
        return uporabnikId;
    }

    public void setUporabnikId(Integer uporabnikId) {
        this.uporabnikId = uporabnikId;
    }
}
