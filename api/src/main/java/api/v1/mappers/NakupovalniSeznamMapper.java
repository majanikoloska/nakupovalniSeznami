package api.v1.mappers;

import DTO.NakupovalniSeznamDto;
import Entities.NakupovalniseznamEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = ArtikelMapper.class)
public interface NakupovalniSeznamMapper {

    NakupovalniseznamEntity mapToNakupovalniseznamEntity(NakupovalniSeznamDto dto);

    NakupovalniSeznamDto mapToNakupovalniSeznamDto(NakupovalniseznamEntity entity);

    List<NakupovalniseznamEntity> mapToNakupovalniseznamEntityList(List<NakupovalniSeznamDto> dtoList);

    List<NakupovalniSeznamDto> mapToNakupovalniSeznamDtoList(List<NakupovalniseznamEntity> entityList);

}
