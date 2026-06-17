package com.portella.granjaconectada.Dto.DtoRequest;

import jakarta.validation.constraints.NotNull;

public record ConsumoRequestDto(
        @NotNull(message = "idade invalida") Integer idadeDias,
        @NotNull(message = "Consumo invalido") Double consumoGramas,
        @NotNull(message = "Peso invalido") Double pesoEsperado
) {
}
