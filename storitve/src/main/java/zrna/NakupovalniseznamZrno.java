package zrna;

import Entities.NakupovalniseznamEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniseznamZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(UporabnikZrno.class.getName());

    public List<NakupovalniseznamEntity> getNakupovalniseznami() {
        List<NakupovalniseznamEntity> nakupovalniseznami = em.createNamedQuery(NakupovalniseznamEntity.GET_ALL).getResultList();
        return nakupovalniseznami;
    }

    public NakupovalniseznamEntity getNakupovalniseznamById(int idNakupovalniseznam) {
        NakupovalniseznamEntity nakupovalniseznam = em.find(NakupovalniseznamEntity.class, idNakupovalniseznam);
        if (nakupovalniseznam == null) {
            logger.info("Nakupovalni seznam ne obstaja");
        }
        return nakupovalniseznam;
    }

    public NakupovalniseznamEntity getNakupovalniseznamByNaziv(String naziv) {
        NakupovalniseznamEntity nakupovalniseznam = em.find(NakupovalniseznamEntity.class, naziv);
        if (nakupovalniseznam == null) {
            logger.info("Nakupovalni seznam ne obstaja");
        }
        return nakupovalniseznam;
    }

    public NakupovalniseznamEntity getNakupovalniSeznamByStatus(String status) {
        NakupovalniseznamEntity nakupovalniseznam = em.find(NakupovalniseznamEntity.class, status);
        if (nakupovalniseznam == null) {
            logger.info("Nakupovalni seznam ne obstaja");
        }
        return nakupovalniseznam;
    }

    @Transactional
    public NakupovalniseznamEntity dodajNakupovalniseznam(NakupovalniseznamEntity nakupovalniseznam) {
        em.persist(nakupovalniseznam);
        logger.info("Uspesno dodan nakupovalni seznam");
        return nakupovalniseznam;
    }

    public NakupovalniseznamEntity prodobiNakupovalniSeznam(int idNakupovalniSeznam) {
        return em.find(NakupovalniseznamEntity.class, idNakupovalniSeznam);
    }

    @Transactional
    public void izbrisiNakupovalniseznam(int idNakupovalniseznam) {
        NakupovalniseznamEntity nakupovalniseznam = em.find(NakupovalniseznamEntity.class, idNakupovalniseznam);
        if (nakupovalniseznam == null) {
            logger.info("Nakupovalni seznam ne obstaja");
        }
        else {
            em.remove(nakupovalniseznam);
        }
    }

    @Transactional
    public void posodobiNakupovalniseznam(NakupovalniseznamEntity nakupovalniseznam) {
        NakupovalniseznamEntity ns = em.find(NakupovalniseznamEntity.class, nakupovalniseznam.getId());
        if (ns == null) {
            logger.info("Nakupovalni seznam ne obstaja");
        }
        ns.setNaziv(nakupovalniseznam.getNaziv());
        ns.setStatus(nakupovalniseznam.getStatus());
        em.merge(ns);
        logger.info("Uspesno posodobljen nakupovalni seznam");
    }
}
