package api.v1.viri;

import DTO.NakupovalniSeznamDto;
import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;
import api.v1.mappers.NakupovalniSeznamMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import zrna.NakupovalniseznamZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/nakupovalniSeznami")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NakupovalniSeznamiVir {


    @Inject
    private NakupovalniseznamZrno nakupovalniseznamZrno;

    @Inject
    private NakupovalniSeznamMapper nakupovalniSeznamMapper;

    @Context
    protected UriInfo uriInfo;

    @GET
    public List<NakupovalniSeznamDto> getNakupovalniseznami() {

        List<NakupovalniseznamEntity> nakupovalniseznamEntities = nakupovalniseznamZrno.getNakupovalniseznami();

        return nakupovalniSeznamMapper.mapToNakupovalniSeznamDtoList(nakupovalniseznamEntities);
    }

    @GET
    @Path("nakupovalniSeznam/{id}")
    public NakupovalniSeznamDto getNakupovalniseznamById(@PathParam("id") int id) {

        NakupovalniseznamEntity nakupovalniseznamEntity = nakupovalniseznamZrno.getNakupovalniseznamById(id);

        return nakupovalniSeznamMapper.mapToNakupovalniSeznamDto(nakupovalniseznamEntity);
    }

    @POST
    public NakupovalniSeznamDto dodajUporabnik(NakupovalniSeznamDto nakupovalniSeznamDto) {

        NakupovalniseznamEntity nakupovalniseznamEntity = nakupovalniSeznamMapper.mapToNakupovalniseznamEntity(nakupovalniSeznamDto);

        nakupovalniseznamEntity = nakupovalniseznamZrno.dodajNakupovalniseznam(nakupovalniseznamEntity);

        return nakupovalniSeznamMapper.mapToNakupovalniSeznamDto(nakupovalniseznamEntity);
    }

    @PUT
    public NakupovalniSeznamDto posodobiUporabnik(NakupovalniSeznamDto nakupovalniSeznamDto) {

        NakupovalniseznamEntity nakupovalniseznamEntity = nakupovalniSeznamMapper.mapToNakupovalniseznamEntity(nakupovalniSeznamDto);

        nakupovalniseznamEntity = nakupovalniseznamZrno.posodobiNakupovalniseznam(nakupovalniseznamEntity);

        return nakupovalniSeznamMapper.mapToNakupovalniSeznamDto(nakupovalniseznamEntity);
    }

    @DELETE
    public void izbrisiUporabnik(NakupovalniSeznamDto nakupovalniSeznamDto) {

        NakupovalniseznamEntity nakupovalniseznamEntity = nakupovalniSeznamMapper.mapToNakupovalniseznamEntity(nakupovalniSeznamDto);

        nakupovalniseznamZrno.izbrisiNakupovalniseznam(nakupovalniseznamEntity.getId());

    }

    @GET
    public Response vrniNakupovalniseznami(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<NakupovalniseznamEntity> nakupovalniseznami = nakupovalniseznamZrno.getNakupovalniseznami2(query);
        Long cnt = nakupovalniseznamZrno.getNakupovalniseznamiCnt(query);
        return Response.ok(nakupovalniseznami).header("X-Total-Count", cnt).build();
    }

}
