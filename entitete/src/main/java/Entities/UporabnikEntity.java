package Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name= UporabnikEntity.GET_ALL,
                        query = "SELECT u FROM UporabnikEntity u"),
                @NamedQuery(name= UporabnikEntity.GET_USER_BY_ID,
                        query = "SELECT u FROM UporabnikEntity u WHERE u.id = :id"),
                @NamedQuery(name= UporabnikEntity.GET_GET_USER_BY_USERNAME,
                        query = "SELECT u FROM UporabnikEntity u WHERE u.username = :username"),
                @NamedQuery(name= UporabnikEntity.GET_GET_USER_BY_EMAIL,
                        query = "SELECT u FROM UporabnikEntity u WHERE u.email = :email"),
                @NamedQuery(name= UporabnikEntity.GET_GET_USER_BY_NAME,
                        query = "SELECT u FROM UporabnikEntity u WHERE u.ime = :ime"),
                @NamedQuery(name= UporabnikEntity.GET_GET_USER_BY_LASTNAME,
                        query = "SELECT u FROM UporabnikEntity u WHERE u.priimek = :priimek")
        })
public class UporabnikEntity {

    public static final String GET_ALL = "UporabnikEntity.getAll";
    public static final String GET_USER_BY_ID = "UporabnikEntity.getById";
    public static final String GET_GET_USER_BY_USERNAME = "UporabnikEntity.getByUsername";
    public static final String GET_GET_USER_BY_EMAIL = "UporabnikEntity.getByNaziv";
    public static final String GET_GET_USER_BY_NAME = "UporabnikEntity.getByName";
    public static final String GET_GET_USER_BY_LASTNAME = "UporabnikEntity.getByLastname";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ime;
    private String priimek;
    private String username;
    private String email;
    private String password;

    @OneToMany(fetch=FetchType.EAGER)
    private List<NakupovalniseznamEntity> nakupovalniseznam;

    public List<NakupovalniseznamEntity> getNakupovalniseznam() {
        return nakupovalniseznam;
    }

    public void setNakupovalniseznam(List<NakupovalniseznamEntity> nakupovalniseznam) {
        this.nakupovalniseznam = nakupovalniseznam;
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
    @Column(name = "ime")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic
    @Column(name = "priimek")
    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UporabnikEntity that = (UporabnikEntity) o;

        if (id != that.id) return false;
        if (ime != null ? !ime.equals(that.ime) : that.ime != null) return false;
        if (priimek != null ? !priimek.equals(that.priimek) : that.priimek != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ime != null ? ime.hashCode() : 0);
        result = 31 * result + (priimek != null ? priimek.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
