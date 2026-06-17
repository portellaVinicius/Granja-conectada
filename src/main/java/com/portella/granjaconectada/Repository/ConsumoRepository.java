package com.portella.granjaconectada.Repository;

import com.portella.granjaconectada.Model.ConsumoModel;
import com.portella.granjaconectada.Model.LotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumoRepository extends JpaRepository<ConsumoModel, Long> {
    Optional<ConsumoModel> findByLoteAndIdadeDias(LotesModel lote, Integer idadeDias);
    Optional<ConsumoModel> findByPadraoTrueAndIdadeDias(Integer idadeDias);
    List<ConsumoModel> findByLoteIdOrderByIdadeDiasAsc(Long id);
    boolean existsByLoteIdAndIdadeDias(Long loteId, Integer idadeDias);
    Optional<ConsumoModel> findByIdadeDias(Integer idadeDias);
}
