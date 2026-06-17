package com.portella.granjaconectada.Controller;

import com.portella.granjaconectada.Dto.DtoResponse.AlertasDto;
import com.portella.granjaconectada.Service.AlertasService;
import com.portella.granjaconectada.mapper.AlertasMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alertas")
public class AlertasController {

    private final AlertasService alertasService;
    private final AlertasMapper alertasMapper;

    @PostMapping("/{loteId}")
    public ResponseEntity<AlertasDto> salvar(@PathVariable Long loteId,
                                             @RequestBody @Valid AlertasDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alertasService.salvar(loteId, dto));
    }

    @GetMapping("/{loteId}")
    public ResponseEntity<List<AlertasDto>> listarPorLote(@PathVariable Long loteId) {
        return ResponseEntity.ok(alertasService.listarPorLoteDto(loteId));
    }

    @GetMapping("/{loteId}/{alertaId}")
    public ResponseEntity<AlertasDto> buscarPorId(@PathVariable Long loteId,
                                                  @PathVariable Long alertaId) {
        return ResponseEntity.ok(alertasMapper.toResponse(alertasService.buscarPorId(loteId, alertaId)));
    }

    @PutMapping("/{loteId}/{alertaId}")
    public ResponseEntity<AlertasDto> atualizar(@PathVariable Long loteId,
                                                @PathVariable Long alertaId,
                                                @RequestBody @Valid AlertasDto dto) {
        return ResponseEntity.ok(alertasService.atualizar(loteId, alertaId, dto));
    }

    @DeleteMapping("/{loteId}/{alertaId}")
    public ResponseEntity<Void> deletar(@PathVariable Long loteId,
                                        @PathVariable Long alertaId) {
        alertasService.deletar(loteId, alertaId);
        return ResponseEntity.noContent().build();
    }
}
