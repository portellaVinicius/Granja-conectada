package com.portella.granjaconectada.Dto.DtoResponse;

public record ComparacaoResponseDto(Double pesoAtual,
                                    Double pesoEsperado,
                                    Double racaoAtual,
                                    Double racaoEsperada,
                                    Double temperatura,
                                    Double temperaturaIdeal,
                                    Double agua,
                                    Double aguaIdeal,
                                    Integer MortalidadeAtual,
                                    Integer MortalidadeEsperada,
                                    Integer idade) {
}
