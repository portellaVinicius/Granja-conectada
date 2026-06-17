package com.portella.granjaconectada.Dto.DtoResponse;


public record DadosResponseDto(
        Double aguaAtual,
        Double racaoAtual,
        float temperaturaAtual,
        Integer mortalidadeAtual,
        Double pesoAtual,
        Integer idade
) {
}
