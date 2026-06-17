package com.portella.granjaconectada.Service;

import com.portella.granjaconectada.Dto.DtoResponse.AlertasDto;
import com.portella.granjaconectada.Exceptions.BussinesExcepiton;
import com.portella.granjaconectada.Exceptions.ExceptionNotFound;
import com.portella.granjaconectada.Model.AlertasModel;
import com.portella.granjaconectada.Model.LotesModel;
import com.portella.granjaconectada.Repository.AlertasRepository;
import com.portella.granjaconectada.mapper.AlertasMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertasService {

    private final AlertasRepository alertasRepository;
    private final LoteService loteService;
    private final AlertasMapper alertasMapper;

    @Transactional
    public AlertasDto salvar(Long loteId, AlertasDto dto){
        LotesModel lotesModel = buscarLote(loteId);

        AlertasModel alertas = alertasMapper.toEntity(dto);
        alertas.setLote(lotesModel);
        alertas.setData(LocalDateTime.now());

        return alertasMapper.toResponse(alertasRepository.save(alertas));
    }

    public AlertasModel buscarPorId(Long loteId, Long alertaId) {
        buscarLote(loteId);
        return alertasRepository.findByIdAndLoteId(alertaId, loteId)
                .orElseThrow(() -> new ExceptionNotFound("Alerta não encontrado"));
    }

    public List<AlertasModel> listarPorLote(Long loteId){
        buscarLote(loteId);
        return alertasRepository.findAlertasModelByLoteId(loteId);
    }

    public List<AlertasDto> listarPorLoteDto(Long loteId) {
        return listarPorLote(loteId)
                .stream()
                .map(alertasMapper::toResponse)
                .toList();
    }

    @Transactional
    public AlertasDto atualizar(Long loteId, Long alertaId, AlertasDto dtoAtualizado) {
        AlertasModel alerta = buscarPorId(loteId, alertaId);

        if (!alerta.getLote().getId().equals(loteId)) {
            throw new BussinesExcepiton("Não é permitido atualizar alerta fora do lote informado");
        }

        alertasMapper.updateFromDto(dtoAtualizado, alerta);

        return alertasMapper.toResponse(alertasRepository.save(alerta));
    }

    @Transactional
    public void deletar(Long loteId, Long alertaId) {
        AlertasModel alerta = buscarPorId(loteId, alertaId);

        if (!alerta.getLote().getId().equals(loteId)) {
            throw new BussinesExcepiton("Não é permitido remover alerta fora do lote informado");
        }

        alertasRepository.delete(alerta);
    }

    private LotesModel buscarLote(Long loteId) {
        return loteService.buscarPorId(loteId);
    }

}
