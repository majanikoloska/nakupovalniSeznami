package servlet;

import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;
import Entities.UporabnikEntity;
import zrna.ArtikelZrno;
import zrna.NakupovalniseznamZrno;
import zrna.UporabnikZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/servlet")
public class JPAServlet extends HttpServlet {

    @Inject
    private UporabnikZrno uporabnikiZrno;

    @Inject
    private ArtikelZrno artikelZrno;

    @Inject
    private NakupovalniseznamZrno nakupovalniseznamZrno;


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


    }
}
