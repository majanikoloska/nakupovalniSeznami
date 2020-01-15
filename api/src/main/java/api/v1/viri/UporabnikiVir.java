package api.v1.viri;

import DTO.UporabnikDto;
import Entities.UporabnikEntity;
import api.v1.mappers.UporabnikMapper;
import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

//    @Inject
//    private UporabnikMapper uporabnikMapper;

    @Context
    protected UriInfo uriInfo;

    @Schema(description = "Returns all users")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Get customers list", tags = {"customers"}, description = "Returns a list of users.", responses = {
            @ApiResponse(description = "List of users", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    UporabnikEntity.class)))
    })

    @GET
    public Response vrniUporabniki(){
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<UporabnikEntity> uporabniki = uporabnikZrno.getUporabniki2(query);
        Long cnt = uporabnikZrno.pridobiUporabnikeCnt(query);
        return Response.ok(uporabniki).header("X-Total-Count", cnt).build();
    }

    @Schema(description = "Add a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Add user", tags = {"customers"}, description = "Adds a user.", responses = {
            @ApiResponse(description = "Add user", responseCode = "201", content = @Content(schema = @Schema(implementation =
                    UporabnikEntity.class)))
    })

    @POST
    public Response dodajUporabnik(UporabnikEntity uporabnik){
        uporabnikZrno.dodajUporabnik(uporabnik);
        return Response.status(Response.Status.CREATED).build();
    }

    @Schema(description = "Update a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Update a user", tags = {"customers"}, description = "Update a user by id.", responses = {
            @ApiResponse(description = "Update a users", responseCode = "200", content = @Content(schema = @Schema(implementation =
                    UporabnikEntity.class)))
    })
    @Path("{id}")
    @PUT
    public Response posodobiUporabnik(UporabnikEntity uporabnik){
        uporabnikZrno.posodobiUporabnika(uporabnik);
        return Response.status(Response.Status.OK).build();
    }

    @Schema(description = "Deletes a user")
    @SecurityRequirement(name = "none")
    @Operation(summary = "Delete user", tags = {"customers"}, description = "Deletes a user by id.", responses = {
            @ApiResponse(description = "Delete user", responseCode = "204", content = @Content(schema = @Schema(implementation =
                    UporabnikEntity.class)))
    })
    @Path("{id}")
    @DELETE
    public Response odstraniUporabnika(@PathParam("id") Integer id) {
        uporabnikZrno.izbrisiUporabnika(id);
        return Response.status(Response.Status.GONE).build();
    }

//    @GET
//    public List<UporabnikDto> vrniUporabnike() {
//
//        List<UporabnikEntity> uporabniki = uporabnikZrno.getUporabniki();
//
//        return uporabnikMapper.mapToUporabnikDtoList(uporabniki);
//    }
//
//    @GET
//    @Path("uporabnik/{id}")
//    public UporabnikDto vrniUporabnikById(@PathParam("id") int id) {
//
//        UporabnikEntity uporabnik = uporabnikZrno.pridobiUporabnika(id);
//
//        return uporabnikMapper.mapToUporabnikDto(uporabnik);
//    }
//
//    @POST
//    public UporabnikDto dodajUporabnik(UporabnikDto uporabnikDto) {
//
//        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);
//
//        uporabnikEntity = uporabnikZrno.dodajUporabnik(uporabnikEntity);
//
//        return uporabnikMapper.mapToUporabnikDto(uporabnikEntity);
//    }
//
//    @PUT
//    public UporabnikDto posodobiUporabnik(UporabnikDto uporabnikDto) {
//
//        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);
//
//        uporabnikEntity = uporabnikZrno.posodobiUporabnika(uporabnikEntity);
//
//        return uporabnikMapper.mapToUporabnikDto(uporabnikEntity);
//    }
//
//    @DELETE
//    public void izbrisiUporabnik(UporabnikDto uporabnikDto) {
//
//        UporabnikEntity uporabnikEntity = uporabnikMapper.mapToUporabnikEntity(uporabnikDto);
//
//        uporabnikZrno.izbrisiUporabnika(uporabnikEntity.getId());
//
//    }

}
