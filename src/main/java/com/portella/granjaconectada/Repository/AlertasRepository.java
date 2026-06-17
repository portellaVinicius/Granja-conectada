package com.portella.granjaconectada.Repository;

import com.portella.granjaconectada.Model.AlertasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlertasRepository extends JpaRepository<AlertasModel, Long> {
    List<AlertasModel> findAlertasModelByLoteId (Long loteId);
    Optional<AlertasModel> findByIdAndLoteId(Long id, Long loteId);
}
