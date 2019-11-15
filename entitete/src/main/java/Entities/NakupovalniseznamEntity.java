package Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "nakupovalniseznam")
@NamedQueries(value =
        {
                @NamedQuery(name= NakupovalniseznamEntity.GET_ALL,
                        query = "SELECT n FROM NakupovalniseznamEntity n"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_ID,
                        query = "SELECT n FROM NakupovalniseznamEntity n WHERE n.id = :id"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_STATUS,
                        query = "SELECT n FROM NakupovalniseznamEntity n WHERE n.status = :status"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_NAZIV,
                        query = "SELECT n FROM NakupovalniseznamEntity n WHERE n.naziv = :naziv")
        })
public class NakupovalniseznamEntity {

    public static final String GET_ALL = "NakupovalniSeznam.getAll";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_ID = "NakupovalniSeznam.getById";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_STATUS = "NakupovalniSeznam.getByStatus";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_NAZIV = "NakupovalniSeznam.getByNaziv";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private String opomba;
    private String naziv;
    private Integer uporabnikId;


    @ManyToMany
    private List<ArtikelEntity> artikli;


    public List<ArtikelEntity> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<ArtikelEntity> artikli) {
        this.artikli = artikli;
    }


    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "opomba")
    public String getOpomba() {
        return opomba;
    }

    public void setOpomba(String opomba) {
        this.opomba = opomba;
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
    @Column(name = "uporabnik_id", nullable = true)
    public Integer getUporabnikId() {
        return uporabnikId;
    }

    public void setUporabnikId(Integer uporabnikId) {
        this.uporabnikId = uporabnikId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NakupovalniseznamEntity that = (NakupovalniseznamEntity) o;
        return id == that.id &&
                Objects.equals(naziv, that.naziv) &&
                Objects.equals(opomba, that.opomba) &&
                Objects.equals(status, that.status) &&
                Objects.equals(uporabnikId, that.uporabnikId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, opomba, status, uporabnikId);
    }
}
