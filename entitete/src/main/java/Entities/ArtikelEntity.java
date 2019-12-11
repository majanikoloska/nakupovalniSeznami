package Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artikel")
@NamedQueries(value =
        {
                @NamedQuery(name= ArtikelEntity.GET_ALL,
                        query = "SELECT a FROM ArtikelEntity a"),
                @NamedQuery(name= ArtikelEntity.GET_ARTIKEL_BY_ID,
                        query = "SELECT a FROM ArtikelEntity a WHERE a.id = :id"),
                @NamedQuery(name= ArtikelEntity.GET_ARTIKEL_BY_NAZIV,
                        query = "SELECT a FROM ArtikelEntity a WHERE a.naziv = :naziv"),
                @NamedQuery(name= ArtikelEntity.GET_ARTIKEL_BY_ZALOGA,
                        query = "SELECT a FROM ArtikelEntity a WHERE a.zaloga = :zaloga"),
                @NamedQuery(name= ArtikelEntity.GET_ARTIKEL_BY_NAKUPOVALNI_SEZNAM_ID,
                        query = "SELECT a FROM ArtikelEntity a WHERE a.nakupovalniseznamId = :nakupovalniSeznamId")
        })
public class ArtikelEntity {

    public static final String GET_ALL = "ArtikelEntity.getAll";
    public static final String GET_ARTIKEL_BY_ID = "ArtikelEntity.getById";
    public static final String GET_ARTIKEL_BY_ZALOGA = "ArtikelEntity.getByZaloga";
    public static final String GET_ARTIKEL_BY_NAZIV = "ArtikelEntity.getByNaziv";
    public static final String GET_ARTIKEL_BY_NAKUPOVALNI_SEZNAM_ID = "ArtikelEntity.getByNakupovalniSeznamId";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String naziv;
    private Boolean zaloga;
    private Integer cena;
    private Integer nakupovalniseznamId;


    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "nakupovalniseznam_id")
    private NakupovalniseznamEntity nakupovalniSeznam;


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

    @Basic
    @Column(name = "nakupovalniseznam_id")
    public Integer getNakupovalniseznamId() {
        return nakupovalniseznamId;
    }

    public void setNakupovalniseznamId(Integer nakupovalniseznamId) {
        this.nakupovalniseznamId = nakupovalniseznamId;
    }


    @Basic
    @Column(name = "cena")
    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public NakupovalniseznamEntity getNakupovalniSeznam() {
        return nakupovalniSeznam;
    }

    public void setNakupovalniSeznam(NakupovalniseznamEntity nakupovalniSeznam) {
        this.nakupovalniSeznam = nakupovalniSeznam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtikelEntity artikel = (ArtikelEntity) o;
        return id == artikel.id &&
                Objects.equals(naziv, artikel.naziv) &&
                Objects.equals(zaloga, artikel.zaloga) &&
                Objects.equals(nakupovalniseznamId, artikel.nakupovalniseznamId) &&
                Objects.equals(cena, artikel.cena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, zaloga, nakupovalniseznamId, cena);
    }
}
