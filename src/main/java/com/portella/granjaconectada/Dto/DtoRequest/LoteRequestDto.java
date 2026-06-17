package com.portella.granjaconectada.Dto.DtoRequest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.portella.granjaconectada.Model.LotesModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record LoteRequestDto(
        @NotNull Integer quantidadeAves,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio
) {
}
