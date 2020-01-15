package api.v1.viri;

import DTO.ArtikelDto;
import Entities.ArtikelEntity;
import api.v1.mappers.ArtikelMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import interceptori.BeleziKlice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Schema(description = "Returns all articles")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get articles list", tags = {"article"}, description = "Returns a list of articles.", responses = {
            @ApiResponse(description = "List of articles", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    ArtikelEntity.class)))
    })

    @GET
    public Response vrniArtikli(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<ArtikelEntity> artikli = artikelZrno.getArtikli2(query);
        Long cnt = artikelZrno.getArtikliCnt(query);
        return Response.ok(artikli).header("X-Total-Count", cnt).build();
    }

    @Schema(description = "Add a articles")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add article", tags = {"article"}, description = "Adds an article.", responses = {
            @ApiResponse(description = "Add article", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    ArtikelEntity.class)))
    })

    @POST
    public Response dodajArtikel(ArtikelEntity artikel){
        artikelZrno.dodajArtikel(artikel);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "Update an article")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update an article", tags = {"article"}, description = "Update an article by id.", responses = {
            @ApiResponse(description = "Update an article", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    ArtikelEntity.class)))
    })


    @PUT
    public Response posodobiArtikel(ArtikelEntity artikel){
        artikelZrno.posodobiArtikel(artikel);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "Deletes an article")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete article", tags = {"article"}, description = "Deletes an article by id.", responses = {
            @ApiResponse(description = "Delete article", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    ArtikelEntity.class)))
    })

    @DELETE
    @Path("{id}")
    public Response odstraniArtikel(@PathParam("id") Integer id) {
        artikelZrno.izbrisiArtikel(id);
        return Response.status(Response.Status.GONE).build();
    }

//    @GET
//    public List<ArtikelDto> getArtikli() {
//
//        List<ArtikelEntity> artikelEntities = artikelZrno.getArtikli();
//
//        return artikelMapper.mapToArtikelDtoList(artikelEntities);
//    }
//
//    @GET
//    @Path("/artikel/{id}")
//    public ArtikelDto getArtikelById(@PathParam("id") int id) {
//
//        ArtikelEntity artikelEntity = artikelZrno.getArtikelById(id);
//
//        return artikelMapper.mapToArtikelDto(artikelEntity);
//    }
//
//    @POST
//    @Path("/artikel/{id}")
//    public ArtikelDto dodajArtikel(ArtikelDto artikelDto) {
//
//        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);
//
//        artikelEntity = artikelZrno.dodajArtikel(artikelEntity);
//
//        return artikelMapper.mapToArtikelDto(artikelEntity);
//    }
//
//    @PUT
//    @Path("/artikel/{id}")
//    public ArtikelDto posodobiArtikel(ArtikelDto artikelDto) {
//
//        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);
//
//        artikelEntity = artikelZrno.posodobiArtikel(artikelEntity);
//
//        return artikelMapper.mapToArtikelDto(artikelEntity);
//    }
//
//    @DELETE
//    @Path("/artikel/{id}")
//    public void izbrisiArtikel(ArtikelDto artikelDto) {
//
//        ArtikelEntity artikelEntity = artikelMapper.mapToArtikelEntity(artikelDto);
//
//        artikelZrno.izbrisiArtikel(artikelEntity.getId());
//
//    }
//
//    @GET
//    @Path("/nakupobvalniSeznam/{id}")
//    public List<ArtikelDto> getArtikelByNakupovalniSeznamId(@PathParam("id") int nakupovalniSeznamId) {
//        List<ArtikelEntity> artikelEntities = artikelZrno.getArtikelByNakupovalniSeznamId(nakupovalniSeznamId);
//
//        return artikelMapper.mapToArtikelDtoList(artikelEntities);
//    }

}
