package com.portella.granjaconectada.Dto.DtoResponse;

public record ComparacaoGenericaDto(
        String tipo,
        Double valorAtual,
        Double valorEsperado
) {
}
