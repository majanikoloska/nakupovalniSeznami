package api.v1.viri;

import DTO.ArtikelDto;
import Entities.ArtikelEntity;
import api.v1.mappers.ArtikelMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import zrna.ArtikelZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/artikli")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ArtikelVir {


    @Inject
    private ArtikelZrno artikelZrno;

    @Inject
    private ArtikelMapper artikelMapper;

    @Context
    protected UriInfo uriInfo;

    @GET
    public List<ArtikelDto> getArtikli() {

        List<ArtikelEntity> artikelEntities = artikelZrno.getArtikli();

        return artikelMapper.mapToArtikelDtoList(artikelEntities);
    }

    @GET
    @Path("/artikel/{id}")
    public ArtikelDto getArtikelById(@PathParam("id") int id) {

        ArtikelEntity artikelEntity = artikelZrno.getArtikelById(id);

        return artikelMapper.mapToArtikelDto(artikelEntity);
    }

    @POST
    public ArtikelDto dodajArtikel(ArtikelDto artikelDto) {

        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);

        artikelEntity = artikelZrno.dodajArtikel(artikelEntity);

        return artikelMapper.mapToArtikelDto(artikelEntity);
    }

    @PUT
    public ArtikelDto posodobiArtikel(ArtikelDto artikelDto) {

        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);

        artikelEntity = artikelZrno.posodobiArtikel(artikelEntity);

        return artikelMapper.mapToArtikelDto(artikelEntity);
    }

    @DELETE
    public void izbrisiArtikel(ArtikelDto artikelDto) {

        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);

        artikelZrno.izbrisiArtikel(artikelEntity.getId());

    }

    @GET
    @Path("/nakupobvalniSeznam/{id}")
    public List<ArtikelDto> getArtikelByNakupovalniSeznamId(@PathParam("id") int nakupovalniSeznamId) {
        List<ArtikelEntity> artikelEntities = artikelZrno.getArtikelByNakupovalniSeznamId(nakupovalniSeznamId);

        return artikelMapper.mapToArtikelDtoList(artikelEntities);
    }

    @GET
    public Response vrniArtikli(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<ArtikelEntity> artikli = artikelZrno.getArtikli2(query);
        Long cnt = artikelZrno.getArtikliCnt(query);
        return Response.ok(artikli).header("X-Total-Count", cnt).build();
    }

}
