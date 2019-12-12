package api.v1.viri;

import DTO.NakupovalniSeznamDto;
import Entities.ArtikelEntity;
import Entities.NakupovalniseznamEntity;
import api.v1.mappers.NakupovalniSeznamMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Schema(description = "Returns all lists")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get lists", tags = {"ShopingList"}, description = "Returns all lists.", responses = {
            @ApiResponse(description = "List of all lists", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    NakupovalniseznamEntity.class)))
    })

    @GET
    public Response vrniNakupovalniseznami(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<NakupovalniseznamEntity> nakupovalniseznami = nakupovalniseznamZrno.getNakupovalniseznami2(query);
        Long cnt = nakupovalniseznamZrno.getNakupovalniseznamiCnt(query);
        return Response.ok(nakupovalniseznami).header("X-Total-Count", cnt).build();
    }

    @Schema(description = "Add a list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add a list", tags = {"ShopingList"}, description = "Adds a list.", responses = {
            @ApiResponse(description = "Add list", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    NakupovalniseznamEntity.class)))
    })
    @POST
    public Response dodajNakupovalniSeznams(NakupovalniseznamEntity nakupovalniseznam){
        nakupovalniseznamZrno.dodajNakupovalniseznam(nakupovalniseznam);
        return Response.status(Response.Status.CREATED).build();
    }


    @Schema(description = "Update a list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update a list", tags = {"ShopingList"}, description = "Update a list by id.", responses = {
            @ApiResponse(description = "Update list by id", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    NakupovalniseznamEntity.class)))
    })
    @Path("{id}")
    @PUT
    public Response posodobiNakupovalniSeznam(NakupovalniseznamEntity nakupovalniseznam){
        nakupovalniseznamZrno.posodobiNakupovalniseznam(nakupovalniseznam);
        return Response.status(Response.Status.OK).build();
    }


    @Schema(description = "Deletes a list")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete list", tags = {"ShopingList"}, description = "Deletes a list by id.", responses = {
            @ApiResponse(description = "Delete list", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    NakupovalniseznamEntity.class)))
    })
    @Path("{id}")
    @DELETE
    public Response odstraniNakupovalniSeznam(@PathParam("id") Integer id) {
        nakupovalniseznamZrno.izbrisiNakupovalniseznam(id);
        return Response.status(Response.Status.GONE).build();
    }

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

}
