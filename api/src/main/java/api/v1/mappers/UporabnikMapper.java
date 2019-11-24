package api.v1.mappers;


import DTO.UporabnikDto;
import Entities.UporabnikEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = NakupovalniSeznamMapper.class)
public interface UporabnikMapper {

    UporabnikEntity mapToUporabnikEntity(UporabnikDto dto);

    UporabnikDto mapToUporabnikDto(UporabnikEntity entity);

    List<UporabnikEntity> mapToUporabnikEntityList(List<UporabnikDto> dtoList);

    List<UporabnikDto> mapToUporabnikDtoList(List<UporabnikEntity> entityList);

}
