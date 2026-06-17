package com.portella.granjaconectada.rules;

import com.portella.granjaconectada.Dto.DtoRequest.LoteRequestDto;
import com.portella.granjaconectada.Exceptions.BussinesExcepiton;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LoteRules {

    public void validarLote(LoteRequestDto dto){
        if (dto.quantidadeAves()<= 0){
            throw new BussinesExcepiton("quantidade de aves não pode ser menor que zero");
        }

        if (dto.dataInicio().isAfter(LocalDate.now())){
            throw new BussinesExcepiton("data incorreta");
        }
    }

}
