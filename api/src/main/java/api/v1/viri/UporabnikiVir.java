package api.v1.viri;

import DTO.UporabnikDto;
import Entities.UporabnikEntity;
import api.v1.mappers.UporabnikMapper;
import zrna.UporabnikZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

}
