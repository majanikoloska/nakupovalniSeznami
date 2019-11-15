package zrna;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

    @PostConstruct
    private void init(){
        logger.info("Inicijalizacija zrna " + UporabnikZrno.class.getSimpleName());
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicijalizacija zrna " + UporabnikZrno.class.getSimpleName());
    }

    public List<UporabnikEntity> getUporabniki() {

        List<UporabnikEntity> uporabniki = em.createNamedQuery(UporabnikEntity.GET_ALL).getResultList();
        return uporabniki;
    }

    @Transactional
    public UporabnikEntity dodajUporabnik(UporabnikEntity uporabnik) {
        em.persist(uporabnik);
        logger.info("Uspesno dodan uporabnik");
        return uporabnik;
    }

    public UporabnikEntity pridobiUporabnika(int idUporabnik) {
        UporabnikEntity uporabnik = em.find(UporabnikEntity.class, idUporabnik);
        if (uporabnik == null) {
            logger.info("Neuspešno pridobivanje uporabnika ");
            return null;
        }
        return uporabnik;
    }

    @Transactional
    public UporabnikEntity posodobiUporabnika(UporabnikEntity uporabnik) {
        UporabnikEntity u = em.find(UporabnikEntity.class, uporabnik.getId());
        if (u == null) {
            logger.info("Uporabnik ne obstaja");
            return null;
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
            return u;
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
