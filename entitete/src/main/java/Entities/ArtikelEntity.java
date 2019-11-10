package Entities;

import javax.persistence.*;

@Entity
@Table(name = "artikel")
public class ArtikelEntity {
    private int id;
    private String naziv;
    private Boolean zaloga;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "naziv")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Basic
    @Column(name = "zaloga")
    public Boolean getZaloga() {
        return zaloga;
    }

    public void setZaloga(Boolean zaloga) {
        this.zaloga = zaloga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtikelEntity that = (ArtikelEntity) o;

        if (id != that.id) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;
        if (zaloga != null ? !zaloga.equals(that.zaloga) : that.zaloga != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        result = 31 * result + (zaloga != null ? zaloga.hashCode() : 0);
        return result;
    }
}
