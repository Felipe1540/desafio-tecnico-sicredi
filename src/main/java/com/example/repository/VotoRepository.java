package com.example.repository;

import com.example.model.Pauta;
import com.example.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    List<Voto> findByPautaId(Pauta pautaId);
    boolean existsByEleitorIdAndPautaId(Long eleitorId, Pauta pautaId);
}
