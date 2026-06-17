package com.portella.granjaconectada.Controller;

import com.portella.granjaconectada.Dto.DtoRequest.DadosRequestDto;
import com.portella.granjaconectada.Dto.DtoResponse.DadosResponseDto;
import com.portella.granjaconectada.Service.DadosService;
import com.portella.granjaconectada.rules.DadosRules;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dados")
public class DadosController {

    private final DadosService dadosService;
    private final DadosRules dadosRules;

    @PostMapping("/{userId}/{loteId}")
    public ResponseEntity<DadosResponseDto> salvar(@PathVariable Long userId, @PathVariable Long loteId,
                                                   @RequestBody @Valid DadosRequestDto dto){
        dadosRules.compararRacao(loteId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosService.salvar(userId, loteId, dto));
    }

    @GetMapping("/previsao/{idade}/{consumoGramas}")
    public ResponseEntity<Double> previsaoPeso(@PathVariable int idade, @PathVariable float consumoGramas){
        Double resultado = dadosService.pesoPrevisto(idade,consumoGramas);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{userId}/{loteId}")
    public ResponseEntity<List<Double>> listarAgua(@PathVariable Long userId, @PathVariable Long loteId){
        List<Double> agua = dadosService.buscarAgua(userId, loteId);
        return ResponseEntity.ok(agua);
    }

    @GetMapping("/{userId}/{loteId}/lista")
    public ResponseEntity<List<DadosResponseDto>> listar(@PathVariable Long userId, @PathVariable Long loteId){
        return ResponseEntity.ok(dadosService.listar(userId, loteId));
    }

    @GetMapping("/{userId}/{loteId}/{id}")
    public ResponseEntity<DadosResponseDto> buscar(@PathVariable Long userId,
                                                   @PathVariable Long loteId,
                                                   @PathVariable Long id) {
        return ResponseEntity.ok(dadosService.buscar(userId, loteId, id));
    }

    @PutMapping("/{userId}/{loteId}/{id}")
    public ResponseEntity<DadosResponseDto> atualizar(@PathVariable Long userId, @PathVariable Long loteId,
                                                      @PathVariable Long id, @RequestBody @Valid DadosRequestDto dto){
        return ResponseEntity.ok(dadosService.atualizar(userId, loteId, id, dto));
    }

    @DeleteMapping("/{userId}/{loteId}/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long userId,
                                        @PathVariable Long loteId,
                                        @PathVariable Long id) {
        dadosService.deletar(userId, loteId, id);
        return ResponseEntity.noContent().build();
    }
}
