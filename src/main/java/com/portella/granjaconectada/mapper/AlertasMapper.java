package com.portella.granjaconectada.mapper;

import com.portella.granjaconectada.Dto.DtoResponse.AlertasDto;
import com.portella.granjaconectada.Model.AlertasModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AlertasMapper {

    AlertasModel toEntity (AlertasDto dto);

    AlertasDto toResponse(AlertasModel alertasModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lote", ignore = true)
    @Mapping(target = "data", ignore = true)
    void updateFromDto(AlertasDto dto, @MappingTarget AlertasModel entity);
}
