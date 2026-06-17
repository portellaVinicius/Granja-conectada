package com.portella.granjaconectada.Repository;

import com.portella.granjaconectada.Model.DadosModel;
import com.portella.granjaconectada.Model.LotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DadosDiariosRepository extends JpaRepository<DadosModel, Long> {
    @Query(value = "SELECT d.aguaAtual FROM DadosModel d WHERE d.lote.id = :loteId")
    List<Double> listarAgua(@Param("loteId") Long loteId);
    List<DadosModel> findDadosDiariosModelByLoteIdOrderByIdAsc(Long loteId);
    Optional<DadosModel> findByIdAndLoteId(Long id, Long loteId);
}
