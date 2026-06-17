package com.portella.granjaconectada.Repository;

import com.portella.granjaconectada.Model.LotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LotesRepository extends JpaRepository<LotesModel, Long> {
    List<LotesModel> findByUserIdOrderByIdAsc(Long userId);
    Optional<LotesModel> findByUserIdAndId(Long userId, Long id);
    
}
