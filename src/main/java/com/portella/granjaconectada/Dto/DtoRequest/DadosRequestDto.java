package com.portella.granjaconectada.Dto.DtoRequest;

import jakarta.validation.constraints.NotNull;

public record DadosRequestDto(
        @NotNull Double aguaAtual,
        @NotNull Double racaoAtual,
        @NotNull float temperaturaAtual,
        @NotNull Integer mortalidadeAtual,
        @NotNull Double pesoAtual,
        @NotNull Integer idade
        ) {
}
