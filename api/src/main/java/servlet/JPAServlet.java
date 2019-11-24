package servlet;

import DTO.NakupovalniSeznamDto;
import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;
import Entities.UporabnikEntity;
import zrna.ArtikelZrno;
import zrna.NakupovalniseznamZrno;
import zrna.UporabnikZrno;
import zrna.UpravljanjeNakupovalnihSeznamovZrno;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikiZrno;

    @Inject
    private ArtikelZrno artikelZrno;

    @Inject
    private NakupovalniseznamZrno nakupovalniseznamZrno;

    @Inject
    private UpravljanjeNakupovalnihSeznamovZrno upravljanjeNakupovalnihSeznamovZrno;

    NakupovalniSeznamDto nakupovalniSeznamDto;

    private final static Logger logger = Logger.getLogger(JPAServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UporabnikEntity> uporabniki = uporabnikiZrno.getUporabniki();

        PrintWriter out = new PrintWriter(resp.getWriter(), true);
        for(UporabnikEntity uporabnik : uporabniki){
            out.println(uporabnik.getId()+", "+uporabnik.getIme()+", "+uporabnik.getPriimek()+
                    ", "+uporabnik.getUsername()+", "+ uporabnik.getEmail());
        }
        List<ArtikelEntity> artikli = artikelZrno.getArtikli();
        for(ArtikelEntity artikel : artikli){
            out.println(artikel.getId()+", "+artikel.getNaziv()+", "+artikel.getZaloga());
        }

        List<NakupovalniseznamEntity> seznam = nakupovalniseznamZrno.getNakupovalniseznami();
        for(NakupovalniseznamEntity nakupovalniseznamEntity : seznam){
            out.println(nakupovalniseznamEntity.getId()+", "+nakupovalniseznamEntity.getNaziv()+", "+nakupovalniseznamEntity.getOpomba()+", "+nakupovalniseznamEntity.getStatus());
        }

        nakupovalniSeznamDto = new NakupovalniSeznamDto();
        nakupovalniSeznamDto.setUporabnikId(1);
        nakupovalniSeznamDto.setArtikli(new ArrayList<>());
        nakupovalniSeznamDto.setNaziv("Food");
        nakupovalniSeznamDto.setStatus("Pending");
        nakupovalniSeznamDto.setOpomba("");

        NakupovalniseznamEntity nakupovalniseznamEntity = upravljanjeNakupovalnihSeznamovZrno.dodajNakupovalniSeznam(nakupovalniSeznamDto);

        logger.info("Dodan nakupovalni seznam z id:" + nakupovalniseznamEntity.getId());

    }
}
