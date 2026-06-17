package com.portella.granjaconectada.Dto.DtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@Email(message = "email inválido") String email,
                             @NotBlank(message = "senha inválida") String senha) {
}
