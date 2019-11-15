package DTO;

public class ArtikelDto {

    public int id;
    public String naziv;
    public Boolean zaloga;
    public Integer nakupovalniseznamId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Boolean getZaloga() {
        return zaloga;
    }

    public void setZaloga(Boolean zaloga) {
        this.zaloga = zaloga;
    }

    public Integer getNakupovalniseznamId() {
        return nakupovalniseznamId;
    }

    public void setNakupovalniseznamId(Integer nakupovalniseznamId) {
        this.nakupovalniseznamId = nakupovalniseznamId;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer cena;

}
