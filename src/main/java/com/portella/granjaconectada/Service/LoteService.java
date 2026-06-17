package com.portella.granjaconectada.Service;

import com.portella.granjaconectada.Dto.DtoRequest.LoteRequestDto;
import com.portella.granjaconectada.Exceptions.ExceptionNotFound;
import com.portella.granjaconectada.mapper.LoteMapper;
import com.portella.granjaconectada.Model.LotesModel;
import com.portella.granjaconectada.Model.UserModel;
import com.portella.granjaconectada.Repository.LotesRepository;
import com.portella.granjaconectada.rules.LoteRules;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LoteService {
    private final LotesRepository lotesRepository;
    private final UserService userService;
    private final LoteMapper loteMapper;
    private final LoteRules loteRules;

    public LotesModel salvar(Long userId, LoteRequestDto loteRequestDto){
        loteRules.validarLote(loteRequestDto);
        UserModel userModel = userService.buscarUser(userId);

        LotesModel lotesModel = loteMapper.toModel(loteRequestDto);
        lotesModel.setUser(userModel);

        return lotesRepository.save(lotesModel);
    }

    public List<LotesModel> listar(Long userId){
        userService.buscarUser(userId);
        return lotesRepository.findByUserIdOrderByIdAsc(userId);
    }
    public LotesModel buscarPorUsuarioIdAndId(Long userId, Long id){
        userService.buscarUser(userId);
        return lotesRepository.findByUserIdAndId(userId,id).orElseThrow(() -> new ExceptionNotFound("Lote não encontrado"));
    }
    public LotesModel buscarPorId(Long id){
        return lotesRepository.findById(id).orElseThrow(() -> new ExceptionNotFound("Id não encontrado"));
    }

    public LotesModel atualizar(Long userId,Long id, LoteRequestDto loteAtualizado){
        loteRules.validarLote(loteAtualizado);
        LotesModel lotesModel = buscarPorUsuarioIdAndId(userId, id);
        lotesModel.setQuantidadeAves(loteAtualizado.quantidadeAves());
        lotesModel.setDataInicio(loteAtualizado.dataInicio());

        return lotesRepository.save(lotesModel);
    }

    public void deletar(Long userId, Long id) {
        LotesModel lotesModel = buscarPorUsuarioIdAndId(userId, id);
        lotesRepository.delete(lotesModel);
    }

}
