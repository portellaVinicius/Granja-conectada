package com.portella.granjaconectada.Service;

import com.portella.granjaconectada.Dto.DtoRequest.ConsumoRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.ConsumoResponseDto;
import com.portella.granjaconectada.Exceptions.BussinesExcepiton;
import com.portella.granjaconectada.Exceptions.ExceptionNotFound;
import com.portella.granjaconectada.Model.ConsumoModel;
import com.portella.granjaconectada.mapper.ConsumoMapper;
import com.portella.granjaconectada.Model.LotesModel;
import com.portella.granjaconectada.Repository.ConsumoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumoRacaoService {
    private final ConsumoRepository consumoRepository;
    private final LoteService loteService;
    private final ConsumoMapper consumoMapper;

    public ConsumoModel buscarPorIdade(Long loteId, Integer idade) {
        LotesModel lotesModel = loteService.buscarPorId(loteId);
        return consumoRepository.findByIdadeDias(idade).orElseThrow(() -> new ExceptionNotFound("Não encontrado"));

    }

    public ConsumoResponseDto buscarPorIdadeResponse(Long loteId, Integer idade) {
        return consumoMapper.toResponse(buscarPorIdade(loteId, idade));
    }

    public List<ConsumoResponseDto> listarPorLoteId(Long loteId){
        loteService.buscarPorId(loteId);

        return consumoRepository.findByLoteIdOrderByIdadeDiasAsc(loteId)
                .stream()
                .map(consumoMapper::toResponse)
                .toList();
    }

    @Transactional
    public ConsumoResponseDto salvar(Long loteId, ConsumoRequestDto dto){
        LotesModel lotesModel = loteService.buscarPorId(loteId);

        if (consumoRepository.existsByLoteIdAndIdadeDias(loteId, dto.idadeDias())) {
            throw new BussinesExcepiton("Já existe consumo cadastrado para essa idade no lote informado");
        }


        ConsumoModel consumo = consumoMapper.toEntity(dto);
        consumo.setLote(lotesModel);
        consumo.setPadrao(false);
        consumoRepository.save(consumo);

        return consumoMapper.toResponse(consumo);

    }

    @Transactional
    public ConsumoResponseDto atualizar(Long loteId, Integer idade, ConsumoRequestDto dto){
        ConsumoModel consumo = buscarPorIdade(loteId, idade);

        consumoMapper.updateFromDto(dto, consumo);
        consumo.setIdadeDias(idade);
        consumo.setPadrao(false);

        consumoRepository.save(consumo);

        return consumoMapper.toResponse(consumo);
    }

    @Transactional
    public void deletar(Long loteId, Integer idade) {
        ConsumoModel consumo = buscarPorIdade(loteId, idade);
        consumoRepository.delete(consumo);
    }
}
