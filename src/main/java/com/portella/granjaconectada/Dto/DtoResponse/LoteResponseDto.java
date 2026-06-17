package com.portella.granjaconectada.Dto.DtoResponse;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record LoteResponseDto(
        Long id,
        Integer quantidadeAves,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio
) {
}
