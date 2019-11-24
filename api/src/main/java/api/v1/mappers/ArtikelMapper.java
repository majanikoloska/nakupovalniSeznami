package api.v1.mappers;

import DTO.ArtikelDto;
import Entities.ArtikelEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ArtikelMapper {

    ArtikelEntity mapToArtikelEntity(ArtikelDto dto);

    ArtikelDto mapToArtikelDto(ArtikelEntity entity);

    List<ArtikelEntity> mapToArtikelEntityList(List<ArtikelDto> dtoList);

    List<ArtikelDto> mapToArtikelDtoList(List<ArtikelEntity> entityList);

}
