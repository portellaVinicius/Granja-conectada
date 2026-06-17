package com.portella.granjaconectada.Dto.DtoResponse;

public record ConsumoResponseDto(
        Long id,
        Integer idadeDias,
        Double consumoGramas,
        Double pesoEsperado
){
}
