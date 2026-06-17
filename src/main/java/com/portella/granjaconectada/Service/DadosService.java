package com.portella.granjaconectada.Service;

import com.portella.granjaconectada.Dto.DtoRequest.DadosRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.DadosResponseDto;
import com.portella.granjaconectada.Exceptions.BussinesExcepiton;
import com.portella.granjaconectada.Exceptions.ExceptionNotFound;
import com.portella.granjaconectada.mapper.DadosMapper;
import com.portella.granjaconectada.Model.DadosModel;
import com.portella.granjaconectada.Model.LotesModel;
import com.portella.granjaconectada.Repository.DadosDiariosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DadosService {
    private final DadosDiariosRepository dadosDiariosRepository;
    private final DadosMapper dadosMapper;
    private final LoteService loteService;

    public Double pesoPrevisto(int idade, float consumoGramas){
        RestTemplate rest = new RestTemplate();
        String url = "http://localhost:8000/predict?dia="+idade+"&consumoGramas=" +consumoGramas;
        return rest.getForObject(url, Double.class);
    }


    @Transactional
    public DadosResponseDto salvar(Long userId, Long loteId, DadosRequestDto dto){
        LotesModel lotesModel = loteService.buscarPorUsuarioIdAndId(userId, loteId);

        DadosModel dadosModel = dadosMapper.toEntity(dto);
        dadosModel.setLote(lotesModel);

        return dadosMapper.toResponse(dadosDiariosRepository.save(dadosModel));
    }

    public DadosModel buscarPorId(Long userId, Long loteId, Long id){
        loteService.buscarPorUsuarioIdAndId(userId, loteId);
        return dadosDiariosRepository.findByIdAndLoteId(id, loteId)
                .orElseThrow(() -> new ExceptionNotFound("Dados não encontrados"));
    }

    public List<Double> buscarAgua(Long userId, Long loteId){
        loteService.buscarPorUsuarioIdAndId(userId, loteId);
        return dadosDiariosRepository.listarAgua(loteId);
    }

    public List<DadosResponseDto> listar(Long userId, Long loteId) {
        loteService.buscarPorUsuarioIdAndId(userId, loteId);
        return dadosDiariosRepository.findDadosDiariosModelByLoteIdOrderByIdAsc(loteId)
                .stream()
                .map(dadosMapper::toResponse)
                .toList();
    }

    public DadosResponseDto buscar(Long userId, Long loteId, Long id) {
        return dadosMapper.toResponse(buscarPorId(userId, loteId, id));
    }

    @Transactional
    public DadosResponseDto atualizar(Long userId, Long loteId, Long id, DadosRequestDto dtoAtualizado){
        LotesModel lotesModel = loteService.buscarPorUsuarioIdAndId(userId, loteId);
        DadosModel dadosModel = buscarPorId(userId, loteId, id);

        if (!dadosModel.getLote().getId().equals(loteId)) {
            throw new BussinesExcepiton("Não é permitido atualizar dados fora do lote informado");
        }

        dadosModel.setLote(lotesModel);
        dadosMapper.updateFromDto(dtoAtualizado, dadosModel);

        DadosModel dadosSalvo = dadosDiariosRepository.save(dadosModel);
        return dadosMapper.toResponse(dadosSalvo);
    }

    @Transactional
    public void deletar(Long userId, Long loteId, Long id) {
        DadosModel dadosModel = buscarPorId(userId, loteId, id);
        dadosDiariosRepository.delete(dadosModel);
    }
}
