package zrna;


import DTO.NakupovalniSeznamDto;
import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;
import Entities.UporabnikEntity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RequestScoped
public class UpravljanjeNakupovalnihSeznamovZrno {

    @Inject
    private ArtikelZrno artikelZrno;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private NakupovalniseznamZrno nakupovalniseznamZrno;


    @PersistenceContext(unitName = "nakupovalniseznam-jpa")
    private EntityManager em;
    private final static Logger logger = Logger.getLogger(UpravljanjeNakupovalnihSeznamovZrno.class.getName());

    @PostConstruct
    private void init(){
        logger.info("Inicijalizacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        logger.info("uuid = " + uuid);
    }

    @PreDestroy
    private void destroy(){
        logger.info("Deinicijalizacija zrna " + UpravljanjeNakupovalnihSeznamovZrno.class.getSimpleName());
    }

    public NakupovalniseznamEntity dodajNakupovalniSeznam(NakupovalniSeznamDto nakupovalniSeznamDto){

        UporabnikEntity uporabnikEntity = uporabnikZrno.pridobiUporabnika(nakupovalniSeznamDto.getUporabnikId());
        if (uporabnikEntity == null){
            logger.info("Uporabnik ne obstaja! Nakupovalni seznam ne bo dodan!");
            return null;
        }
        NakupovalniseznamEntity nakupovalniseznam = new NakupovalniseznamEntity();
        nakupovalniseznam.setUporabnikId(nakupovalniSeznamDto.getUporabnikId());
        nakupovalniseznam.setNaziv(nakupovalniSeznamDto.getNaziv());
        nakupovalniseznam.setOpomba(nakupovalniSeznamDto.getOpomba());
        nakupovalniseznam.setStatus(nakupovalniSeznamDto.getStatus());
        nakupovalniseznam.setArtikli(nakupovalniSeznamDto.getArtikli());

        return nakupovalniseznamZrno.dodajNakupovalniseznam(nakupovalniseznam);
    }

    public Boolean validirajNakupovalniSeznam(NakupovalniSeznamDto nakupovalniSeznamDto){
        if (nakupovalniSeznamDto.getNaziv().isEmpty() || nakupovalniSeznamDto.getNaziv() == null
                || nakupovalniSeznamDto.getStatus() == null || nakupovalniSeznamDto.getStatus().isEmpty()){
//            logger.info("Seznam ne vsebuje vseh obveznih podatkov");
//            return false;
            throw new Error();
        }
        return true;
    }

    public Integer narediIzracun(NakupovalniSeznamDto nakupovalniSeznamDto){
        List<ArtikelEntity> artikelEntities = artikelZrno.getArtikelByNakupovalniSeznamId(nakupovalniSeznamDto.getId());
        if (artikelEntities.isEmpty() || artikelEntities == null){
            throw new Error();
        }
        Integer izracun = 0;
        for (ArtikelEntity artikelEntity: artikelEntities){
            izracun += artikelEntity.getCena();
        }
        return izracun;
    }


}
