package com.portella.granjaconectada.rules;

import com.portella.granjaconectada.Dto.DtoRequest.DadosRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.AlertasDto;
import com.portella.granjaconectada.Model.ConsumoModel;
import com.portella.granjaconectada.Service.AlertasService;
import com.portella.granjaconectada.Service.ConsumoRacaoService;
import com.portella.granjaconectada.Service.LoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Getter
@Setter
public class DadosRules {
    private final AlertasService alertasService;
    private final ConsumoRacaoService consumoRacaoService;
    private final LoteService loteService;

    public void compararRacao(Long loteid, DadosRequestDto dto) {
        ConsumoModel consumoModel = consumoRacaoService.buscarPorIdade(loteid, dto.idade());

        double consumoAtual = dto.racaoAtual();
        double consumoEsperado = consumoModel.getConsumoGramas();
        double tolerancia = 0.1;

        if (consumoAtual > consumoEsperado + tolerancia){
            AlertasDto alertasDto = new AlertasDto("Consumo Alto", "Ração", "Atentar",dto.racaoAtual(), consumoModel.getConsumoGramas());
            alertasService.salvar(loteid, alertasDto);
            return;
        }

        if (consumoAtual < consumoEsperado - tolerancia) {
            AlertasDto alertasDto = new AlertasDto("Consumo Baixo","Ração", "Atentar", consumoAtual, consumoEsperado);
            alertasService.salvar(loteid, alertasDto);
        }

    }



}
