package com.portella.granjaconectada.mapper;

import com.portella.granjaconectada.Dto.DtoRequest.ConsumoRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.ConsumoResponseDto;
import com.portella.granjaconectada.Model.ConsumoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ConsumoMapper {

    ConsumoModel toEntity(ConsumoRequestDto dto);

    ConsumoResponseDto toResponse(ConsumoModel consumoModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "padrao", ignore = true)
    @Mapping(target = "lote", ignore = true)
    void updateFromDto(ConsumoRequestDto dto, @MappingTarget ConsumoModel entity);
}
