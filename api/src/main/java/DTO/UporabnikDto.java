package DTO;

import Entities.NakupovalniseznamEntity;

import java.util.List;

public class UporabnikDto {

    public int id;
    public String ime;
    public String priimek;
    public String username;
    public String email;
    public String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NakupovalniseznamEntity> getNakupovalniseznam() {
        return nakupovalniseznam;
    }

    public void setNakupovalniseznam(List<NakupovalniseznamEntity> nakupovalniseznam) {
        this.nakupovalniseznam = nakupovalniseznam;
    }

    public List<NakupovalniseznamEntity> nakupovalniseznam;

}
