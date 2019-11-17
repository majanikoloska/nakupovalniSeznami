package zrna;

import Entities.ArtikelEntity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@ApplicationScoped
public class ArtikelZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(ArtikelZrno.class.getName());

    @PostConstruct
    private void init(){
        logger.info("Inicijalizacija zrna " + ArtikelZrno.class.getSimpleName());
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        logger.info("uuid = " + uuid);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicijalizacija zrna " + ArtikelZrno.class.getSimpleName());
    }

    public List<ArtikelEntity> getArtikli() {
        List<ArtikelEntity> artikli = em.createNamedQuery(ArtikelEntity.GET_ALL).getResultList();
        return artikli;
    }

    public ArtikelEntity getArtikelById(int idArtikel) {
        ArtikelEntity artikel = em.find(ArtikelEntity.class, idArtikel);
        if (artikel == null) {
            logger.info("Artikel ne obstaja");
        }
        return artikel;
    }

    public List<ArtikelEntity> getArtikelByNakupovalniSeznamId(int nakupovalniSeznamId){
        Query q = em.createNamedQuery(ArtikelEntity.GET_ARTIKEL_BY_NAKUPOVALNI_SEZNAM_ID).setParameter("nakupovalniSeznamId", nakupovalniSeznamId);
        List<ArtikelEntity> artikli =(List<ArtikelEntity>)(q.getResultList());
        if (artikli == null) {
            logger.info("Artikel z nakupovalni seznam id =" + nakupovalniSeznamId + " ne obstaja!");
            return null;
        }
        return artikli;

    }

    @Transactional
    public ArtikelEntity dodajArtikel(ArtikelEntity artikel) {
        em.persist(artikel);
        logger.info("Uspesno dodan artikel");
        return artikel;
    }

    @Transactional
    public ArtikelEntity posodobiArtikel(ArtikelEntity artikel) {
        ArtikelEntity a = em.find(ArtikelEntity.class, artikel.getId());
        if (a == null) {
            logger.info("Artikel ne obstaja");
            return null;
        }
        a.setNaziv(artikel.getNaziv());
        a.setZaloga(artikel.getZaloga());
        em.merge(a);
        logger.info("Uspesno posodobljen artikel");
        return a;
    }

    @Transactional
    public void izbrisiArtikel(int idArtikel) {
        ArtikelEntity artikel = em.find(ArtikelEntity.class, idArtikel);
        if (artikel == null) {
            logger.info("Artikel ne obstaja");
        }
        else {
            em.remove(artikel);
        }
    }
}
