package zrna;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.*;

import java.util.List;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;

    public List<UporabnikEntity> getUporabniki() {

        List<UporabnikEntity> uporabniki = em.createNamedQuery(UporabnikEntity.GET_ALL).getResultList();
        return uporabniki;
    }

    public List<ArtikelEntity> getArtikel(){
        return em.createNamedQuery(ArtikelEntity.GET_ALL).getResultList();
    }

    public List<NakupovalniseznamEntity> getNakupovalniSeznam(){
        return em.createNamedQuery(NakupovalniseznamEntity.GET_ALL).getResultList();
    }
}
