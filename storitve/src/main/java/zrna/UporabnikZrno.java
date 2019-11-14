package zrna;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import Entities.*;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    public List<UporabnikEntity> getUporabniki() {

        List<UporabnikEntity> uporabniki = em.createNamedQuery(UporabnikEntity.GET_ALL).getResultList();
        return uporabniki;
    }

    public UporabnikEntity getUporabnikById(int idUporabnik) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, idUporabnik);
        if (uporabnik == null) {
            logger.info("Neuspesno");
        }
        return uporabnik;
    }

    public UporabnikEntity getUporabnikByName(String uporabniName) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, uporabniName);
        if (uporabnik == null) {
            logger.info("Neuspesno");
        }
        return uporabnik;
    }

    public UporabnikEntity getUporabnikBySurname(String uporabniSuername) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, uporabniSuername);
        if (uporabnik == null) {
            logger.info("Neuspesno");
        }
        return uporabnik;
    }

    public UporabnikEntity getUporabnikByUsername(String uporabnikUsername) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, uporabnikUsername);
        if (uporabnik == null) {
            logger.info("Neuspesno");
        }
        return uporabnik;
    }

    public UporabnikEntity getUporabnikByEmail(String uporabnikEmail) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, uporabnikEmail);
        if (uporabnik == null) {
            logger.info("Neuspesno");
        }
        return uporabnik;
    }

    @Transactional
    public UporabnikEntity dodajUporabnik(UporabnikEntity uporabnik) {
        em.persist(uporabnik);
        logger.info("Uspesno dodan uporabnik");
        return uporabnik;
    }

    public UporabnikEntity pridobiUporabnika(int idUporabnik) {
        return em.find(UporabnikEntity.class, idUporabnik);
    }

    @Transactional
    public void posodobiUporabnika(UporabnikEntity uporabnik) {
        UporabnikEntity u = em.find(UporabnikEntity.class, uporabnik.getId());
        if (u == null) {
            logger.info("Uporabnik ne obstaja");
        }
        else {
            u.setIme(uporabnik.getIme());
            u.setPriimek(uporabnik.getPriimek());
            u.setUsername(uporabnik.getUsername());
            u.setEmail(uporabnik.getEmail());
            u.setPassword(uporabnik.getPassword());
            u.setNakupovalniseznam(uporabnik.getNakupovalniseznam());
            em.merge(u);
            logger.info("Uspesno posodobljen uporabnik");
        }
    }

    @Transactional
    public void izbrisiUporabnika(int idUporabnik) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, idUporabnik);
        if (uporabnik == null) {
            logger.info("Uporabnik ne obstaja");
        }
        else {
            em.remove(uporabnik);
        }
    }

}
