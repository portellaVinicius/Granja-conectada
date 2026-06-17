package com.portella.granjaconectada.Controller;

import com.portella.granjaconectada.Dto.DtoRequest.LoteRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.LoteResponseDto;
import com.portella.granjaconectada.mapper.LoteMapper;
import com.portella.granjaconectada.Model.LotesModel;
import com.portella.granjaconectada.Service.LoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lotes")
public class LoteController {

    private final LoteService loteService;
    private final LoteMapper loteMapper;

    @PostMapping("/{userId}")
    public ResponseEntity<LoteResponseDto> salvar(@PathVariable Long userId ,@RequestBody @Valid LoteRequestDto dto){
        LotesModel loteSalvo = loteService.salvar(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(loteMapper.fromModel(loteSalvo));
    }
    @GetMapping("/{userId}/{id}")
    public ResponseEntity<LoteResponseDto> buscarPorUsuario(@PathVariable Long userId, @PathVariable Long id){
        LotesModel lotesModel = loteService.buscarPorUsuarioIdAndId(userId, id);
        return ResponseEntity.ok(loteMapper.fromModel(lotesModel));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoteResponseDto>> listarPorId(@PathVariable Long userId){

        List<LoteResponseDto> lotesModels = loteService.listar(userId)
                .stream()
                .map(loteMapper::fromModel)
                .toList();
        return ResponseEntity.ok(lotesModels);
    }

    @PutMapping("/{userId}/{id}")
    public ResponseEntity<LoteResponseDto> atualizar(@PathVariable Long userId, @PathVariable Long id ,@RequestBody @Valid LoteRequestDto dto){
        LotesModel lotesModel = loteService.atualizar(userId, id, dto);
        return ResponseEntity.ok(loteMapper.fromModel(lotesModel));
    }

    @DeleteMapping("/{userId}/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long userId, @PathVariable Long id) {
        loteService.deletar(userId, id);
        return ResponseEntity.noContent().build();
    }
}
