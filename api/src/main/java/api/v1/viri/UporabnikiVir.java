package api.v1.viri;

import DTO.UporabnikDto;
import Entities.UporabnikEntity;
import api.v1.mappers.UporabnikMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import zrna.UporabnikZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private UporabnikMapper uporabnikMapper;

    @Context
    protected UriInfo uriInfo;

    @GET
    public List<UporabnikDto> vrniUporabnike() {

        List<UporabnikEntity> uporabniki = uporabnikZrno.getUporabniki();

        return uporabnikMapper.mapToUporabnikDtoList(uporabniki);
    }

    @GET
    @Path("uporabnik/{id}")
    public UporabnikDto vrniUporabnikById(@PathParam("id") int id) {

        UporabnikEntity uporabnik = uporabnikZrno.pridobiUporabnika(id);

        return uporabnikMapper.mapToUporabnikDto(uporabnik);
    }

    @POST
    public UporabnikDto dodajUporabnik(UporabnikDto uporabnikDto) {

        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);

        uporabnikEntity = uporabnikZrno.dodajUporabnik(uporabnikEntity);

        return uporabnikMapper.mapToUporabnikDto(uporabnikEntity);
    }

    @PUT
    public UporabnikDto posodobiUporabnik(UporabnikDto uporabnikDto) {

        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);

        uporabnikEntity = uporabnikZrno.posodobiUporabnika(uporabnikEntity);

        return uporabnikMapper.mapToUporabnikDto(uporabnikEntity);
    }

    @DELETE
    public void izbrisiUporabnik(UporabnikDto uporabnikDto) {

        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);

        uporabnikZrno.izbrisiUporabnika(uporabnikEntity.getId());

    }

    public Response vrniUporabniki(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<UporabnikEntity> uporabniki = uporabnikZrno.getUporabniki2(query);
        Long cnt = uporabnikZrno.pridobiUporabnikeCnt(query);
        return Response.ok(uporabniki).header("X-Total-Count", cnt).build();
    }

}
