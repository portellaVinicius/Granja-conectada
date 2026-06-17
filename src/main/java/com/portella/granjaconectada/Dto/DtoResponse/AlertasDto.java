package com.portella.granjaconectada.Dto.DtoResponse;

public record AlertasDto(
        String messagem,
        String tipo,
        String nivel,
        Double valorAtual,
        Double valorEsperado
) {
}
