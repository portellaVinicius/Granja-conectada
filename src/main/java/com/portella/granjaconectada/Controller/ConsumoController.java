package com.portella.granjaconectada.Controller;

import com.portella.granjaconectada.Dto.DtoRequest.ConsumoRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.ConsumoResponseDto;
import com.portella.granjaconectada.Service.ConsumoRacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/Consumo", "/consumos"})
public class ConsumoController {
    private final ConsumoRacaoService consumoRacaoService;

    @PostMapping("/{loteId}")
    public ResponseEntity<ConsumoResponseDto> salvar(@PathVariable Long loteId,
                                                     @RequestBody @Valid ConsumoRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(consumoRacaoService.salvar(loteId, dto));
    }

    @GetMapping("/{loteId}")
    public ResponseEntity<List<ConsumoResponseDto>> listar(@PathVariable Long loteId){
        return ResponseEntity.ok(consumoRacaoService.listarPorLoteId(loteId));
    }

    @GetMapping("/{loteId}/{idade}")
    public ResponseEntity<ConsumoResponseDto> buscarPorIdade(@PathVariable Long loteId,
                                                             @PathVariable Integer idade ){
        return ResponseEntity.ok(consumoRacaoService.buscarPorIdadeResponse(loteId, idade));
    }


    @PatchMapping("/{loteId}/{idade}")
    public ResponseEntity<ConsumoResponseDto> atualizar(@PathVariable Long loteId,
                                                        @PathVariable Integer idade,
                                                        @RequestBody @Valid ConsumoRequestDto dto){
        return ResponseEntity.ok(consumoRacaoService.atualizar(loteId, idade, dto));
    }

    @DeleteMapping("/{loteId}/{idade}")
    public ResponseEntity<Void> deletar(@PathVariable Long loteId,
                                        @PathVariable Integer idade) {
        consumoRacaoService.deletar(loteId, idade);
        return ResponseEntity.noContent().build();
    }
}
