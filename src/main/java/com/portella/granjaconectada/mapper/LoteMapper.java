package com.portella.granjaconectada.mapper;

import com.portella.granjaconectada.Dto.DtoRequest.LoteRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.LoteResponseDto;
import com.portella.granjaconectada.Model.LotesModel;
import org.springframework.stereotype.Component;

@Component
public class LoteMapper {

    public LotesModel toModel(LoteRequestDto loteRequestDto){
        if (loteRequestDto == null) return null;
        LotesModel lotesModel = new LotesModel();

        lotesModel.setQuantidadeAves(loteRequestDto.quantidadeAves());
        lotesModel.setDataInicio(loteRequestDto.dataInicio());

        return lotesModel;
    }

    public LoteResponseDto fromModel(LotesModel lotesModel){
        if (lotesModel == null) return null;
        return new LoteResponseDto(
                lotesModel.getId(),
                lotesModel.getQuantidadeAves(),
                lotesModel.getDataInicio());
    }

}
