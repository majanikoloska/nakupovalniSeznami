package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nakupovalniseznam")
@NamedQueries(value =
        {
                @NamedQuery(name= NakupovalniseznamEntity.GET_ALL,
                        query = "SELECT n FROM nakupovalniseznam n"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_ID,
                        query = "SELECT n FROM nakupovalniseznam n WHERE n.id = :id"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_STATUS,
                        query = "SELECT n FROM nakupovalniseznam n WHERE n.status = :status"),
                @NamedQuery(name= NakupovalniseznamEntity.GET_NAKUPOVALNI_SEZNAM_BY_NAZIV,
                        query = "SELECT n FROM nakupovalniseznam n WHERE n.naziv = :naziv")
        })
public class NakupovalniseznamEntity {

    public static final String GET_ALL = "NakupovalniSeznam.getAll";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_ID = "NakupovalniSeznam.getById";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_STATUS = "NakupovalniSeznam.getByStatus";
    public static final String GET_NAKUPOVALNI_SEZNAM_BY_NAZIV = "NakupovalniSeznam.getByNaziv";

    @Id
    private int id;
    private String status;
    private String opomba;
    private String naziv;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NakupovalniseznamEntity that = (NakupovalniseznamEntity) o;

        if (id != that.id) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (opomba != null ? !opomba.equals(that.opomba) : that.opomba != null) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (opomba != null ? opomba.hashCode() : 0);
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        return result;
    }
}
