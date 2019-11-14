package zrna;

import Entities.ArtikelEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ArtikelZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(ArtikelZrno.class.getName());

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

    public ArtikelEntity getArtikelByNaziv(String naziv) {
        ArtikelEntity artikel = em.find(ArtikelEntity.class, naziv);
        if (artikel == null) {
            logger.info("Artikel ne obstaja");
        }
        return artikel;
    }

    public ArtikelEntity getArtikelByZaloga(String zaloga) {
        ArtikelEntity artikel = em.find(ArtikelEntity.class, zaloga);
        if (artikel == null) {
            logger.info("Artikel ne obstaja");
        }
        return artikel;
    }

    @Transactional
    public ArtikelEntity dodajArtikel(ArtikelEntity artikel) {
        em.persist(artikel);
        logger.info("Uspesno dodan artikel");
        return artikel;
    }

    public ArtikelEntity pridobiArtikel(int idArtikel) {
        return em.find(ArtikelEntity.class, idArtikel);
    }

    @Transactional
    public void posodobiArtikel(ArtikelEntity artikel) {
        ArtikelEntity a = em.find(ArtikelEntity.class, artikel.getId());
        if (a == null) {
            logger.info("Artikel ne obstaja");
        }
        a.setNaziv(artikel.getNaziv());
        a.setZaloga(artikel.getZaloga());
        em.merge(a);
        logger.info("Uspesno posodobljen artikel");
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
