package com.portella.granjaconectada.mapper;

import com.portella.granjaconectada.Dto.DtoRequest.DadosRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.DadosResponseDto;
import com.portella.granjaconectada.Model.DadosModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface DadosMapper {
    DadosModel toEntity(DadosRequestDto dto);

    DadosResponseDto toResponse(DadosModel dadosModel);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lote", ignore = true)
    void updateFromDto(DadosRequestDto dto, @MappingTarget DadosModel entity);
}
