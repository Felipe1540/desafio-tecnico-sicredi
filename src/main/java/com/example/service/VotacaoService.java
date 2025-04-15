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

    private PautaRepository pautaRepository;
    private VotoRepository votoRepository;

    // Metodo para cadastro da pauta
    public Pauta cadastrarPauta(String descricao) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(descricao);

        return pautaRepository.save(pauta);
    }

    // Metodo para registro do voto
    public Voto cadastrarVoto(Long eleitorId, Long pautaId, String votoDesc) {
        Voto voto = new Voto();
        voto.setEleitorId(eleitorId);
        voto.setPautaId(pautaId);
        voto.setVoto(votoDesc);

        return votoRepository.save(voto);
    }
}
