package zrna;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Entities.*;
import javax.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class UporabnikZrno {

    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;

    public List<UporabnikEntity> getUporabniki() {

        List<UporabnikEntity> uporabniki = em.createNamedQuery(UporabnikEntity.GET_ALL).getResultList();
        return uporabniki;
    }
}
