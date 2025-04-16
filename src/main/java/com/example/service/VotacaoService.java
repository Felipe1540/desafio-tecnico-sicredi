package com.example.service;

import com.example.model.Pauta;
import com.example.model.Voto;
import com.example.repository.PautaRepository;
import com.example.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    // Metodo para cadastro da pauta
    public Pauta cadastrarPauta(String descricao) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(descricao);

        return pautaRepository.save(pauta);
    }

    // Metodo para registro do voto
    public void cadastrarVoto(Long eleitorId, Long pautaId, String votoDesc) {
        if (!votoDesc.equalsIgnoreCase("Sim") && !votoDesc.replace("Ã", "A").equalsIgnoreCase("Não")) {
            throw new RuntimeException("O voto deve ser 'Sim' ou 'Não'.");
        }

        if (votoRepository.existsByEleitorIdAndPautaId(eleitorId, pautaId)){
            Voto voto = new Voto();
            voto.setEleitorId(eleitorId);
            voto.setPautaId(pautaId);
            voto.setVoto(votoDesc);
            votoRepository.save(voto);
        } else {
            throw new RuntimeException("É permitido apenas 1 voto por pauta para cada eleitor.");
        }
    }
}
