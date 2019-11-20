package zrna;

import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class NakupovalniseznamZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(NakupovalniseznamZrno.class.getName());

    @PostConstruct
    private void init(){
        logger.info("Inicijalizacija zrna " + NakupovalniseznamZrno.class.getSimpleName());
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        logger.info("uuid = " + uuid);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicijalizacija zrna " + NakupovalniseznamZrno.class.getSimpleName());
    }

    public List<NakupovalniseznamEntity> getNakupovalniseznami() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<NakupovalniseznamEntity> c = cb.createQuery(NakupovalniseznamEntity.class);
        Root<NakupovalniseznamEntity> root = c.from(NakupovalniseznamEntity.class);
        c.select(root);
        List<NakupovalniseznamEntity> nakupovalniseznami = em.createQuery(c).getResultList();
        return nakupovalniseznami;
    }

    public NakupovalniseznamEntity getNakupovalniseznamById(int idNakupovalniseznam) {
        NakupovalniseznamEntity nakupovalniseznam = em.find(NakupovalniseznamEntity.class, idNakupovalniseznam);
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
    public NakupovalniseznamEntity posodobiNakupovalniseznam(NakupovalniseznamEntity nakupovalniseznam) {
        NakupovalniseznamEntity ns = em.find(NakupovalniseznamEntity.class, nakupovalniseznam.getId());
        if (ns == null) {
            logger.info("Nakupovalni seznam ne obstaja");
            return null;
        }
        ns.setNaziv(nakupovalniseznam.getNaziv());
        ns.setStatus(nakupovalniseznam.getStatus());
        em.merge(ns);
        logger.info("Uspesno posodobljen nakupovalni seznam");
        return ns;
    }
}
