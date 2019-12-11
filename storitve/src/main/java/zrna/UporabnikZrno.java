package zrna;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import Entities.*;
import interceptori.BeleziKlice;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
@BeleziKlice
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    @PostConstruct
    private void init(){
        logger.info("Inicijalizacija zrna " + UporabnikZrno.class.getSimpleName());
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        logger.info("uuid = " + uuid);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicijalizacija zrna " + UporabnikZrno.class.getSimpleName());
    }

    public List<UporabnikEntity> getUporabniki() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UporabnikEntity> c = cb.createQuery(UporabnikEntity.class);
        Root<UporabnikEntity> root = c.from(UporabnikEntity.class);
        c.select(root);

        List<UporabnikEntity> uporabniki = em.createQuery(c).getResultList();
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
