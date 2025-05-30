package com.example.service;

import com.example.dto.PautaDTO;
import com.example.dto.VotoDTO;
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
    public void cadastrarVoto(VotoDTO votoDto) {
        Pauta pauta = pautaRepository.findById(votoDto.getPautaId())
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        if (!pauta.isSessaoAberta()) {
            throw new RuntimeException("A sessão de votação para esta pauta está encerrada.");
        }

        if (votoRepository.existsByEleitorIdAndPautaId(votoDto.getEleitorId(), pauta)) {
            throw new RuntimeException("É permitido apenas 1 voto por pauta para cada eleitor.");
        }

        Voto voto = new Voto();
        voto.setEleitorId(votoDto.getEleitorId());
        voto.setPautaId(pauta);
        voto.setVoto(votoDto.getVoto());
        votoRepository.save(voto);
    }

    // Contagem de votos "sim" ou "não"
    public String contagemDeVotos(Long pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        List<Voto> votos = votoRepository.findByPautaId(pauta);

        // buscando dentro de votos e somando sim e não dentro da variavel
        long sim = votos.stream().filter(v -> "SIM".equalsIgnoreCase(v.getVoto())).count();
        long nao = votos.stream().filter(v -> "NÃO".equalsIgnoreCase(v.getVoto())).count();

        return "Contagem de votos da pauta: " + pauta.getDescricao() + "\n" +
                "SIM = " + sim + "\n" +
                "NÃO = " + nao;
    }

    // busca de votos por pauta, retorna a lista completa para o id pauta que for passado
    public List<Voto> BuscarVotosPorPauta(Long pautaId) {
        Pauta pauta = pautaRepository.findById(pautaId)
                .orElseThrow(() -> new RuntimeException("Pauta não encontrada"));

        List<Voto> votos = votoRepository.findByPautaId(pauta);

        return votos;
    }

    // busca por pautas, retorna todas as pautas cadastradas, com sessões abertas ou não.
    public List<Pauta> BuscarPautas() {
        List<Pauta> pautas = pautaRepository.findAll();

        return pautas;
    }


}
