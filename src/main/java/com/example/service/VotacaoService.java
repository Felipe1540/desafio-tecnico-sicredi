package com.example.service;

import com.example.dto.PautaDTO;
import com.example.model.Pauta;
import com.example.model.Voto;
import com.example.repository.PautaRepository;
import com.example.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotacaoService {

    private final PautaRepository pautaRepository;
    private final VotoRepository votoRepository;

    // Metodo para cadastro da pauta
    public Pauta cadastrarPauta(PautaDTO pautaDto) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(pautaDto.getDescricao());
        pauta.setDataCriacao(LocalDateTime.now()); //pegando hora atual da criacao para depois traçar o tempo

        //se não for informado, define o padrão de 1 mninuto para a pauta
        Long duracao = pautaDto.getDuracaoEmMinutos() != null ? pautaDto.getDuracaoEmMinutos() : 1L;
        pauta.setDuracaoEmMinutos(duracao);

        return pautaRepository.save(pauta);
    }

    // Metodo para registro do voto
    public void cadastrarVoto(Long eleitorId, Long pautaId, String votoDesc) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        if (!votoRepository.existsByEleitorIdAndPautaId(eleitorId, pauta)){
            Voto voto = new Voto();
            voto.setEleitorId(eleitorId);
            voto.setPautaId(pauta);
            voto.setVoto(votoDesc);
            votoRepository.save(voto);
        } else {
            throw new RuntimeException("É permitido apenas 1 voto por pauta para cada eleitor.");
        }
    }

    // Contagem de votos "sim" ou "não"
    public String contagemDeVotos(Long pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        List<Voto> votos = votoRepository.findByPautaId(pauta);
        int sim = 0;
        int nao = 0;

        for (Voto voto : votos) {
            String valor = voto.getVoto();

            if ("SIM".equals(valor)) {
                sim++;
            }
            if ("NÃO".equals(valor)) {
                nao++;
            }
        }

        return "Contagem de votos da pauta: " + pauta.getDescricao() + "\n" +
                "SIM = " + sim + "\n" +
                "NÃO = " + nao;
    }
}
